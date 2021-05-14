import java.util.*;

public class BreadthFirstSearch {

    private  static Map<Integer, List<Integer>> getAdjacencyList(List<List<Integer>> edges, int vertices){
        Map<Integer, List<Integer>> adjacencyList = new HashMap<Integer, List<Integer>>();
        for(int i=0;i<vertices;i++){
            List<Integer> emptyConnectionsList = new ArrayList<>();
            adjacencyList.put(i,emptyConnectionsList);
        }
        for (int i= 0; i < edges.size(); i++) {
            List<Integer> preReqElement = edges.get(i);
            int u = preReqElement.get(0);
            int v = preReqElement.get(1);
            List<Integer> oldConnections = adjacencyList.get(u);
			oldConnections.add(v);
            adjacencyList.put(u, oldConnections);
        }
        return  adjacencyList;
    }

        public static int[] breadthFirstSearch(List<List<Integer>> edges, int vertices) {
            Queue<Integer> Q = new LinkedList<>();
            int[] parent = new int[vertices], distance = new int[vertices];
            boolean[] visited = new boolean[vertices];

            if (vertices == 0)
                return distance;
			
            int startVertex = 0; //given
            Arrays.fill(distance, -1); //fill all distances as -1(not found)
            Q.add(startVertex);
            visited[startVertex] = true;
            distance[startVertex] = 0; //we start seeing the distance from vertex 0

            Map<Integer, List<Integer>> adjacencyList = getAdjacencyList(edges, vertices);

            while (! Q.isEmpty()) {
                int u = Q.remove(); //first in, first out-> so even if we keep adding elements of the next level to the queue, it wont be covered until we finish previous level
                for (int v : adjacencyList.get(u)) {
                    if (! visited[v]){
                        distance[v] = distance[u] + 1;
                        parent[v] = u;
                        Q.add(v);
                        visited[v] = true;
                    }
                }
            }

        return  distance;

        }
}
