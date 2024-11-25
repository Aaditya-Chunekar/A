import java.util.*;

class PrimMST {

    // A class to represent an edge between two cities
    static class Edge {
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Function to implement Prim's Algorithm for Minimum Spanning Tree (MST)
    public static void primMST(int[][] graph, int numCities) {
        // Priority Queue to select the edge with minimum weight
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        // Array to keep track of whether a city is included in MST or not
        boolean[] inMST = new boolean[numCities];

        // Array to store the minimum cost of connecting each city
        int[] key = new int[numCities];
        Arrays.fill(key, Integer.MAX_VALUE); // Initialize all costs as infinity

        // Start from city 0 (arbitrary choice)
        key[0] = 0;
        pq.offer(new Edge(0, 0)); // Add city 0 with cost 0 to priority queue

        int totalCost = 0;

        while (!pq.isEmpty()) {
            // Select the edge with the minimum weight
            Edge currentEdge = pq.poll();
            int city = currentEdge.dest;

            // If the city is already included in MST, skip it
            if (inMST[city]) continue;

            // Include this city in the MST
            inMST[city] = true;
            totalCost += currentEdge.weight;

            // Update the key values of the neighboring cities
            for (int neighbor = 0; neighbor < numCities; neighbor++) {
                if (!inMST[neighbor] && graph[city][neighbor] != 0 && graph[city][neighbor] < key[neighbor]) {
                    key[neighbor] = graph[city][neighbor];
                    pq.offer(new Edge(neighbor, graph[city][neighbor]));
                }
            }
        }

        System.out.println("Total cost of constructing the road system (MST): " + totalCost);
    }

    public static void main(String[] args) {
        // Sample graph representing cities and road construction costs
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        int numCities = 5; // Number of cities (nodes)

        // Run Prim's Algorithm to find the MST and total cost
        primMST(graph, numCities);
    }
}
