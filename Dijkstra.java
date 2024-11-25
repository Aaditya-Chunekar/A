import java.util.*;

public class CourierService {

    // Class to represent an edge between two nodes
    static class Edge {
        int destination, weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Dijkstra's algorithm to find the shortest paths
    public static void dijkstra(List<List<Edge>> graph, int source) {
        int numNodes = graph.size();
        int[] distances = new int[numNodes];
        boolean[] visited = new boolean[numNodes];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Initialize distances to infinity and mark all nodes unvisited
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Add the source node to the priority queue
        priorityQueue.offer(new int[]{source, 0});

        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int currentNode = current[0];

            // Skip if the node has already been processed
            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            // Relax the edges of the current node
            for (Edge edge : graph.get(currentNode)) {
                int neighbor = edge.destination;
                int newDist = distances[currentNode] + edge.weight;

                if (!visited[neighbor] && newDist < distances[neighbor]) {
                    distances[neighbor] = newDist;
                    priorityQueue.offer(new int[]{neighbor, newDist});
                }
            }
        }

        // Print shortest distances from the source node
        System.out.println("Shortest distances from source node " + source + ":");
        for (int i = 0; i < numNodes; i++) {
            System.out.println("To node " + i + ": " + (distances[i] == Integer.MAX_VALUE ? "Infinity" : distances[i]));
        }
    }

    // Main method to simulate the system
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of locations (nodes): ");
        int numLocations = scanner.nextInt();

        System.out.print("Enter the number of roads (edges): ");
        int numEdges = scanner.nextInt();

        // Create a graph as an adjacency list
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numLocations; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter the roads as source, destination, and distance:");
        for (int i = 0; i < numEdges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.get(source).add(new Edge(destination, weight));
            graph.get(destination).add(new Edge(source, weight)); // For undirected graph
        }

        System.out.print("Enter the source location: ");
        int source = scanner.nextInt();

        dijkstra(graph, source);
        scanner.close();
    }
}
