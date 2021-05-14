import java.util.ArrayList;
import java.util.List;

public class CriticalLink {
    public int criticalLink(int n, int[][] links) {
        List<List<Integer>> criticalLinks=new ArrayList<>();
        List<List<Integer>> adjacencyLinks=new ArrayList<>();

        for(int i=0;i<n;i++)
            adjacencyLinks.add(new ArrayList<>());

        for(int i=0; i < links.length; i++){
            int x = links[i][0];
            int y = links[i][1];
            adjacencyLinks.get(x).add(y);
            adjacencyLinks.get(y).add(x);
        }

        int d[] = new int[n];

        getCriticalLinks(0,adjacencyLinks,d,new int[n],new boolean[n],1,0,criticalLinks);
        return criticalLinks.size();
    }

    public void getCriticalLinks(int start, List<List<Integer>> con, int discovery[], int lowDiscovery[], boolean visited[], int d, int parent, List<List<Integer>> criticalLinks){
        discovery[start]=lowDiscovery[start]=d;
        visited[start]=true;
        for(int i:con.get(start)){
            if(!visited[i]){
                d++;
                getCriticalLinks(i,con,discovery,lowDiscovery,visited,d,start,criticalLinks);
            }
            if(i != parent)
                lowDiscovery[start] = Math.min(lowDiscovery[start],lowDiscovery[i]);
        }

        if(discovery[parent] < lowDiscovery[start]){
            List<Integer> time= new ArrayList<>();
            time.add(parent);
            time.add(start);
            criticalLinks.add(time);
        }
    }
}

