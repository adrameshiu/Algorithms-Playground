import java.util.*;

public class MinimumSpanningTree {
    static  Map<Integer, GraphNode> allVerticesMap = new HashMap<Integer, GraphNode>();

    static class GraphNode {
        GraphNode(int name, int p, int w, int r) { vertex = name; parent=p; weight=w;rank=r;};

        int vertex;
        int parent;
        int rank; 
        int weight;
    }

        public static int mst(ArrayList<ArrayList<Integer>> edges, int vertices) {
        ArrayList<ArrayList<Integer>> minSpanningTree = new ArrayList<>();
        int totalCost = 0;


        for (int v=0; v<vertices; v++){
            makeVertex(v);
        }

        Collections.sort(edges,(e1, e2) -> e1.get(2) - e2.get(2));

        edges.forEach((edge)->{
            int u = edge.get(0); //0th index is u
            int v = edge.get(1); //1st index is v
            int uRep = find(u); int vRep = find(v);

            //only if both dont belong to the same connected component, then include edge
            if(uRep != vRep){
                union(u,v);
                minSpanningTree.add(edge);
            }
        });

        totalCost = minSpanningTree.stream()
                    .mapToInt((edge) -> edge.get(2))
                    .sum();

        return totalCost;
        }


    public static void makeVertex(int vertex) {
        GraphNode newVertex =new GraphNode(vertex, vertex, Integer.MAX_VALUE,0);
        allVerticesMap.put(vertex, newVertex);
    }


  //time complexity -> O(1)
    public static int union(int u, int v) {
        int uRepVertex = find(u);
        int vRepVertex = find(v);
        int newRep = uRepVertex;

        GraphNode uRepresentative = allVerticesMap.get(uRepVertex);
        GraphNode vRepresentative = allVerticesMap.get(vRepVertex);

        if(uRepresentative.rank > vRepresentative.rank){
            vRepresentative.parent = uRepVertex;
            vRepresentative.rank = vRepresentative.rank + uRepresentative.rank;
            newRep = uRepVertex;

        } else if (vRepresentative.rank > uRepresentative.rank) {
            uRepresentative.parent = vRepVertex;
            uRepresentative.rank = uRepresentative.rank + vRepresentative.rank;
            newRep = vRepVertex;

        } else if (uRepresentative.rank == vRepresentative.rank) {
            vRepresentative.parent = uRepVertex;
            uRepresentative.rank = uRepresentative.rank + 1;
            newRep = uRepVertex;
        }
        return newRep;
    }


    //time complexity-> O(1) amortized
    public static int find(int vertex) {
        GraphNode searchedVertex = allVerticesMap.get(vertex);
        if(searchedVertex.vertex == searchedVertex.parent) return searchedVertex.vertex;

        //path compression
        searchedVertex.parent = find(searchedVertex.parent);
        return searchedVertex.parent;

    }

}
