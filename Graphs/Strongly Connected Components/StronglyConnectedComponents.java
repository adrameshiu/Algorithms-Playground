import java.util.*;

public class StronglyConnectedComponents {
    public static List<List<Integer>> scc(int students, List<List<Integer>> knows) {
        List<List<Integer>> stronglyConnectedComponents = new ArrayList<>();
        List<List<Integer>> knowsTranspose = new ArrayList<>();

        int[] nodes = new int[students];

        nodes = createStudents(students);

        knowsTranspose = findAdjacencyListTranspose(knows);

        //converting the list of lists to a hash map for accessing the list of connected nodes directly
        Map<Integer, List<Integer>> knowsConnections = getAllConnections(knows);
        Map<Integer, List<Integer>> knowsTransposeConnections = getAllConnections(knowsTranspose);

        //DFS on the transposed graph -> to get the order in which the nodes are traversed
        Stack<Integer> finishTimes = DepthFirstSearch(students, knowsTransposeConnections);

        stronglyConnectedComponents = getConnectedComponents(students, finishTimes, knowsConnections);

        return stronglyConnectedComponents;
    }


    public static List<List<Integer>> getConnectedComponents(int studentCount, Stack<Integer> rootNodeVisitOrder,  Map<Integer, List<Integer>> G) {
        boolean[] visited = new boolean[studentCount];
        for (int u = 0; u < studentCount; ++u) {
            visited[u] = false;
        }
        Stack<Integer> finishTimeOrder = new Stack<>();
        List<List<Integer>> connectedComponentsList = new ArrayList<>();
        while (rootNodeVisitOrder.size() > 0) {
            Integer u = rootNodeVisitOrder.pop();
            List<Integer> connectedComponentsForRootU = new ArrayList<>();
            //connectedComponentsForRootU.add(u);
            if (! visited[u]){

                connectedComponentsForRootU = DepthFirstSearchVisit(G, u, visited, finishTimeOrder, connectedComponentsForRootU);
                connectedComponentsList.add(connectedComponentsForRootU);
            }
        }
        return connectedComponentsList;
    }



    static Stack<Integer> DepthFirstSearch(int studentCount, Map<Integer, List<Integer>> G) {
        boolean[] visited = new boolean[studentCount];
        for (int u = 0; u < studentCount; ++u) {
            visited[u] = false;
        }
        Stack<Integer> finishTimeOrder = new Stack<>(); //time to finish looking up all their children
        List<Integer> connectedComponentsForRootU = new ArrayList<>();
        for (int u = 0; u < studentCount; ++u) {
            if (! visited[u])
                DepthFirstSearchVisit(G, u, visited, finishTimeOrder, connectedComponentsForRootU);
        }
        return finishTimeOrder;
    }

    static List<Integer> DepthFirstSearchVisit(Map<Integer, List<Integer>> G, int u,
                                               boolean[] visited,
                                               Stack<Integer> finishTime, List<Integer> subGraph) {

        //subGraph.add(u);
        visited[u] = true;

        List<Integer> vList = G.get(u);
        if(vList != null) {
        for (int v:vList){
            if (! visited[v]){
                DepthFirstSearchVisit(G, v, visited, finishTime, subGraph); //go to the last leaf node and then only add to finish time when it is over
            }
        }
        }

        finishTime.add(u);
        subGraph.add(u);

        return subGraph;


    }




    private  static Map<Integer, List<Integer>> getAllConnections(List<List<Integer>> knws){
        Map<Integer, List<Integer>> connections = new HashMap<Integer, List<Integer>>();

        for (int i= 0; i < knws.size(); i++) {
            List<Integer> emptyConnectionsList = new ArrayList<>();
            List<Integer> preReqElement = knws.get(i);
            int u = preReqElement.get(0);
            int v = preReqElement.get(1);
            List<Integer> oldConnections = connections.get(u)!=null ? connections.get(u) : emptyConnectionsList;
            oldConnections.add(v);
            connections.put(u, oldConnections);
        }
        return  connections;
    }


    //in knows transpose, graph is opposite-> if u knows v in knows, v knows u in knows_transpose
    private static List<List<Integer>> findAdjacencyListTranspose(List<List<Integer>> A){
        List<List<Integer>> ATranspose = new ArrayList<>();
        for (int i=0;i<A.size();i++){
            int u_transpose = A.get(i).get(1); //need to get v and put into u
            int v_transpose = A.get(i).get(0); //need to get v and put into u
            List<Integer> transposeElement = new ArrayList<>();
            transposeElement.add(u_transpose);
            transposeElement.add(v_transpose);
            ATranspose.add(transposeElement);
        }
        return  ATranspose;

    }

    //create students between the range [0,studentCount)
    private static int[] createStudents(int studentCount){
        int[] students = new int[studentCount];
        for (int i=0;i<studentCount;i++)
            students[i] = i;
        return students;
    }



}
