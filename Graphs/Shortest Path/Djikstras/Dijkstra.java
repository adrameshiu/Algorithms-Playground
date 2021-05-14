import java.util.*;

public class Dijkstra {
    public static int[] shortestDistance(List<List<Integer>> edges, int vertices) {
        List<List<int[][]>> con=new ArrayList<>();
        for(int i=0;i<vertices;i++)
            con.add(new ArrayList<>());
        for(int i=0; i < edges.size(); i++){
            int source = edges.get(i).get(0);
            int des = edges.get(i).get(1);
            int weight = edges.get(i).get(2);
            con.get(source).add(new int[][]{{des , weight}});
        }
        boolean[] visited = new boolean[vertices];
        int[] distance = new int[vertices];
        Arrays.fill(distance,Integer.MAX_VALUE);
        if(vertices == 0)
            return distance;
        int start = 0;
        Queue<Integer> Q = new LinkedList<Integer>();
        Q.add(start);
        distance[start] = 0;
        while (! Q.isEmpty()) {
            int current = Q.remove();
            List<int[][]> neighbours = con.get(current);
            for (int[][] pair : neighbours) {
                    if ((distance[current] + pair[0][1]) < distance[pair[0][0]]) {
                        distance[pair[0][0]] = distance[current] + pair[0][1];
                        Q.add(pair[0][0]);
                    }
            }
        }
        for (int i=0; i < vertices; i++)
        {
            if(distance[i] == Integer.MAX_VALUE)
                distance[i] = -1;
        }

        return distance;
    }

}
