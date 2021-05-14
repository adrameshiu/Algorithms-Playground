import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bipartiteness {
    public static int bipartite(List<List<Integer>> edges, int vertices) {
            int bipart = 1;

        if (vertices==0) return  1;

        Map<Integer, List<Integer>> edgesAdjacencyList = getAdjacencyList(edges);

        boolean isBipartite =   DepthFirstSearch(vertices, edgesAdjacencyList);
        if (!isBipartite) bipart = -1; //if not bipartite, we have to return -1
            return  bipart;
    }

    static boolean DepthFirstSearchVisit(Map<Integer, List<Integer>> G, int u,int parentU,
                                               boolean[] visited, int[] setNumber) {
        visited[u] = true;
        setNumber[u] = (setNumber[parentU] == 1) ? 2 : 1; //should be opposite of the parent set..-1 is only for not found
        List<Integer> vList = G.get(u);

        if(vList != null) {
            for (int v:vList){
                if (! visited[v]){

                    boolean isBipartite = DepthFirstSearchVisit(G, v, u, visited, setNumber); //go to the last leaf node and then only add to finish time when it is over
                    if (isBipartite == false) return  false;
                }
                else { //if already visited, if the parent and the child have the same set, then it is not bipartite
                        if ( setNumber[u] == setNumber[v]) return  false;
                }
            }
        }


        return true;
    }


    static boolean DepthFirstSearch(int vertices, Map<Integer, List<Integer>> G) {
        boolean[] visited = new boolean[vertices];
        int[] setNumber = new int[vertices]; //dividing them to two sets 1 and 2, -1 for not yet run
        boolean isBipartite= true;
        for (int u = 0; u < vertices; ++u) {
            visited[u] = false;
            setNumber[u] = -1;
        }
        for (int u = 0; u < vertices; ++u) {
            if (! visited[u]) isBipartite = DepthFirstSearchVisit(G, u, u, visited, setNumber);
            if (isBipartite == false) return  false; //to ensure we return if we get any false
        }
        return isBipartite;
    }

    private  static Map<Integer, List<Integer>> getAdjacencyList(List<List<Integer>> edgesList){
        Map<Integer, List<Integer>> edgesAdjacency = new HashMap<Integer, List<Integer>>();

        for (int i= 0; i < edgesList.size(); i++) {
            List<Integer> emptyConnectionsListU = new ArrayList<>();
            List<Integer> emptyConnectionsListV = new ArrayList<>();
            List<Integer> preReqElement = edgesList.get(i);
            int u = preReqElement.get(0);
            int v = preReqElement.get(1);

            List<Integer> oldEdgesU = edgesAdjacency.get(u)!=null ? edgesAdjacency.get(u) : emptyConnectionsListU;
            oldEdgesU.add(v);
            edgesAdjacency.put(u, oldEdgesU);

            //we need an UNDIRECTED adjacency list here..so u->v and v->u
            List<Integer> oldEdgesV = edgesAdjacency.get(v)!=null ? edgesAdjacency.get(v) : emptyConnectionsListV;
            oldEdgesV.add(u);
            edgesAdjacency.put(v, oldEdgesV);
        }
        return  edgesAdjacency;
    }


    }