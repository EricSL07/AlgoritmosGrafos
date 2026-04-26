import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, Vertex> vertices = new HashMap<>();
    
    private final Map<Vertex, List<Vertex>> adjacencyList = new HashMap<>();

    public void addVertex(String id, String label) {
        Vertex v = new Vertex(id, label);

        vertices.put(id, v);

        adjacencyList.computeIfAbsent(v, k -> new ArrayList<>());
    }

    // 2. Implemente este método
    public void addEdge(String sourceId, String targetId) {
        Vertex source = vertices.get(sourceId);
        Vertex target = vertices.get(targetId);

        if (source == null || target == null) {
            throw new IllegalArgumentException("Vértice não encontrada");
        }

        adjacencyList.get(source).add(target);
        adjacencyList.get(target).add(source);

        // Pegue o Vértice source e o target do mapa 'vertices'.
        // Como o grafo é NÃO DIRIGIDO, adicione o target na lista do source, 
        // e adicione o source na lista do target.
    }

    // Métodos utilitários já prontos para as próximas fases
    public Vertex getVertex(String id) {
        return vertices.get(id);
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    public List<Vertex> getNeighbors(Vertex v) {
        return adjacencyList.getOrDefault(v, new ArrayList<>());
    }
    
    public int getVertexCount() {
        return vertices.size();
    }
}