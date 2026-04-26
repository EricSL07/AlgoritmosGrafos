import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GraphMetrics {
    private final Graph graph;

    public GraphMetrics(Graph graph) {
        this.graph = graph;
    }

    private Map<Vertex, Integer> calculateShortestPaths(Vertex start) {
        Map<Vertex, Integer> distances = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();

        queue.add(start);
        distances.putIfAbsent(start, 0);

        while (!queue.isEmpty()) {
            Vertex atual = queue.poll();

            for (Vertex vizinho : graph.getNeighbors(atual)) {
                if (!distances.containsKey(vizinho)) {
  
                    int novaDistancia = distances.get(atual) + 1;
                    
                    distances.put(vizinho, novaDistancia);
                    
                    queue.add(vizinho);
                }
            }
        }

        return distances;
    }

    public int calculateEccentricity(Vertex v) { // cite: 24
        Map<Vertex, Integer> distances = calculateShortestPaths(v);
        
        int valor = distances.values().stream()
            .max(Integer::compareTo)
            .orElse(0);
        
        return valor;
    }

    public double calculateClosenessCentrality(Vertex v) {
        Map<Vertex, Integer> distances = calculateShortestPaths(v);

        int N = graph.getVertexCount();

        double sumDistances = distances.values().stream()
            .mapToInt(Integer::intValue)
            .sum();

        if (sumDistances == 0) {
            return 0.0;
        }

        return (double) (N - 1) / sumDistances;
    }
}