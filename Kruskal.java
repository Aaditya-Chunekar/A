import java.util.*;

class KruskalMST {

    // Edge class to represent a connection between two cities with its cost
    static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Disjoint Set (Union-Find) data structure to track connected components
    static class DisjointSet {
        int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // Find the representative (root) of the set that a node belongs to
        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);  // Path compression
            }
            return parent[u];
        }

        // Union of two sets
        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU != rootV) {
                // Union by rank
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }

    // Function to implement Kruskal's algorithm to find the MST
    public static void kruskalMST(int numCities, Edge[] edges) {
        // Sort edges based on their weight
        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));

        DisjointSet ds = new DisjointSet(numCities);
        int totalCost = 0;

        // List to store the edges included in the MST
        List<Edge> mst = new ArrayList<>();

        // Iterate through the sorted edges
        for (Edge edge : edges) {
            int u = edge.src;
            int v = edge.dest;
            int weight = edge.weight;

            // If u and v are in different sets, include this edge in MST
            if (ds.find(u) != ds.find(v)) {
                ds.union(u, v);
                mst.add(edge);
                totalCost += weight;
            }
        }

        // Output the MST edges and total cost
        System.out.println("Minimum Spanning Tree (MST) Edges:");
        for (Edge edge : mst) {
            System.out.println("City " + edge.src + " - City " + edge.dest + " with cost " + edge.weight);
        }
        System.out.println("Total cost of constructing the road system (MST): " + totalCost);
    }

    public static void main(String[] args) {
        // Example of cities and road construction costs (edges)
        int numCities = 5;
        Edge[] edges = new Edge[] {
            new Edge(0, 1, 2),  // Road from city 0 to city 1 with cost 2
            new Edge(0, 3, 6),  // Road from city 0 to city 3 with cost 6
            new Edge(1, 2, 3),  // Road from city 1 to city 2 with cost 3
            new Edge(1, 3, 8),  // Road from city 1 to city 3 with cost 8
            new Edge(1, 4, 5),  // Road from city 1 to city 4 with cost 5
            new Edge(2, 4, 7),  // Road from city 2 to city 4 with cost 7
            new Edge(3, 4, 9)   // Road from city 3 to city 4 with cost 9
        };

        // Run Kruskal's Algorithm to find the MST and total cost
        kruskalMST(numCities, edges);
    }
}
