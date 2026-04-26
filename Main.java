import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        GexfParser parser = new GexfParser();
        Graph grafo = parser.parse("Grafo.gexf"); 
        
        GraphMetrics metrics = new GraphMetrics(grafo);

        try (
            PrintWriter writerEcc = new PrintWriter(new FileWriter("eccentricity.txt"));
            PrintWriter writerClose = new PrintWriter(new FileWriter("closeness_centrality.txt"))
        ) {
            System.out.println("Iniciando cálculos...");

            for (Vertex v : grafo.getVertices()) {
                
                int ecc = metrics.calculateEccentricity(v);
                double closeCent = metrics.calculateClosenessCentrality(v);

                System.out.printf("Vértice: %-15s | Eccentricity: %d | Closeness: %.4f%n", 
                                  v.label(), ecc, closeCent);

                writerEcc.println(v.label() + " : " + ecc);
                writerClose.println(v.label() + " : " + closeCent);
            }

            System.out.println("Arquivos gerados com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao escrever os arquivos: " + e.getMessage());
        }
    }
}