import java.util.*;

class Graph {
    private int V;
    private List<List<Node>> adj;

    Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int source, int destination, int weight) {
        Node node = new Node(destination, weight);
        adj.get(source).add(node);
    }

    static class Node {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}

public class DijkstraAlgorithm {
    private static void dijkstra(Graph graph, int source) {
        int V = graph.V;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Graph.Node> minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        minHeap.add(new Graph.Node(source, 0));

        while (!minHeap.isEmpty()) {
            int u = minHeap.poll().vertex;

            for (Graph.Node neighbor : graph.adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    minHeap.add(new Graph.Node(v, dist[v]));
                }
            }
        }

        // Print the distances from the source to all vertices
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 6;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 5);

        int source = 0;
        dijkstra(graph, source);
    }
}
