import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Undirected graph with adjacency lists, following the style from
 * Sedgewick & Wayne (Algorithms, 4th ed.).
 */
public class Graph {
    private final int vertices;
    private int edges;
    private final List<List<Integer>> adj;

    public Graph(int vertices) {
        if (vertices < 0) {
            throw new IllegalArgumentException("Numero de vertices não deve ser negativo");
        }
        this.vertices = vertices;
        this.edges = 0;
        this.adj = new ArrayList<>(vertices);
        for (int v = 0; v < vertices; v++) {
            adj.add(new ArrayList<>());
        }
    }

    public int V() {
        return vertices;
    }

    public int E() {
        return edges;
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj.get(v).add(w);
        adj.get(w).add(v);
        edges++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return Collections.unmodifiableList(adj.get(v));
    }

    public int degree(int v) {
        validateVertex(v);
        return adj.get(v).size();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertices - 1));
        }
    }
}
