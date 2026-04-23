import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeitorGexf {
    public static void main(String[] args) {
        try {
            // Carregando o arquivo GEXF
            File arquivoXmL = new File("Grafo.gexf");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(arquivoXmL);
    
            // Formatar o XML
            doc.getDocumentElement().normalize();
    
            System.out.println("Lendo o primeiro elemento: " + doc.getDocumentElement().getNodeName());
            
            // Extratir os nós
            NodeList listaNos = doc.getElementsByTagName("node");
            int n = listaNos.getLength();
            System.out.println("Total de Nos encontrados: " + n);
            
            // Criar o grafo
            Graph grafo = new Graph(n);
            Map<String, Integer> idToIndex = new HashMap<>();
            List<String> labelsByIndex = new ArrayList<>(n);


            for (int i = 0; i < n; i++) {
                Node no = listaNos.item(i);
                Element elemento = (Element) no;
                String id = elemento.getAttribute("id");
                String label = elemento.getAttribute("label");

                idToIndex.put(id, i);
                labelsByIndex.add(label == null || label.isBlank() ? id : label);
                
                System.out.println("Nó -> ID " + id + " | Label: " + label);
            }

            System.out.println("-----------------------------");

            // Extrair as Arestas
            NodeList ListaArestas = doc.getElementsByTagName("edge");
            int m = ListaArestas.getLength();
            System.out.println("Total de Arestas encontradas " + m);

            for (int i = 0; i < m; i++) {
                Node aresta = ListaArestas.item(i);
                if (aresta.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) aresta;
                    String source = elemento.getAttribute("source");
                    String target = elemento.getAttribute("target");
                    String weigth = elemento.getAttribute("weigth");

                    System.out.println("Aresta -> Origem " + source + " conecta ao destino: " + target);
                    if (!weigth.isEmpty()) {
                        System.out.println(" | Peso " + weigth);
                    }
                    System.out.println();
                }
            }


        } catch (Exception e) {
            System.err.println("Erro ao ler o aquivo GEFX: " + e.getMessage());
            e.printStackTrace();
        }
    }
}