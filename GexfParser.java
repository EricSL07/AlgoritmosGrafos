import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class GexfParser {

    public Graph parse(String filePath) {
        Graph graph = new Graph();

        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("node");
            
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String id = eElement.getAttribute("id");
                    String label = eElement.getAttribute("label");

                    graph.addVertex(id, label); 
                }
            }

            NodeList eList = doc.getElementsByTagName("edge");
            
            for (int i = 0; i < eList.getLength(); i++) {
                Node eNode = eList.item(i);
                
                if (eNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) eNode;
                    
                    String source = eElement.getAttribute("source");
                    String target = eElement.getAttribute("target");

                    graph.addEdge(source, target);
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo XML: " + e.getMessage());
            e.printStackTrace();
        }

        return graph;
    }
}