package leetcode.graph;

/**
 * A minimum spanning tree (MST) or minimum weight spanning tree is a Subset of
 * the edges of a connected, edge-weighted (un)directed graph that connects all
 * the vertices together, without any cycles and with the minimum possible total edge weight.
 * That is, it is a spanning tree whose sum of edge weights is as small as possible.
 * <p>
 * Prim's algorithm is a greedy algorithm.
 * 1) Create a set mstSet that keeps track of vertices already included in MST.
 * 2) Assign a key value to all vertices in the input graph. Initialize all key values as INFINITE. Assign key value as 0 for the first vertex so that it is picked first.
 * 3) While mstSet doesn’t include all vertices
 * a) Pick a vertex u which is not there in mstSet and has minimum key value.
 * b) Include u to mstSet.
 * c) Update key value of all adjacent vertices of u. To update the key values,
 * iterate through all adjacent vertices. For every adjacent vertex v,
 * if weight of edge u-v is less than the previous key value of v,
 * update the key value as weight of u-v
 */
public class MinimumSpanningTreePrim {

    // A utility function to find the vertex with minimum key
    // value, from the set of vertices not yet included in MST
    private static int minKey(int key[], Boolean mstSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
    private static void printMST(int parent[], int graph[][]) {
        System.out.println("Edge   Weight");
        for (int i = 1; i < graph.length; i++) {
            System.out.println(parent[i] + " - " + i + "    " +
                    graph[i][parent[i]]);
        }
    }

    // Function to construct and print MST for a graph represented
    //  using adjacency matrix representation
    private static void primMST(int graph[][]) {
        int V = graph.length;
        // Array to store constructed MST
        int parent[] = new int[V];

        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];

        // To represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0;     // Make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent
            // vertices of the picked vertex. Consider only those
            // vertices which are not yet included in MST
            for (int v = 0; v < V; v++) {
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // print the constructed MST
        printMST(parent, graph);
    }

    public static void main(String[] args) {
        /* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
        int graph[][] = new int[][]{{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };

        // Print the solution
        primMST(graph);
    }
}
