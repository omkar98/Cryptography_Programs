import java.util.*;
public class MinNodeDist {
    public static void main(String[] args) {
        // int N = 2;
        Scanner sc= new Scanner(System.in);
        int N= sc.nextInt();
        // System.out.println("N: "+ N);
        for(int i=0;i<N; i++)
        {
          int[][] edges = new int[50][2];
          int e = sc.nextInt();
          for(int j1=0; j1<e-1; j1++)
          {
            for(int j2=0; j2<2; j2++)
            {
              int edge = sc.nextInt();
              edges[j1][j2] = edge;
            }
          }
          System.out.println("Case #"+(i+1)+": "+getMinDisFarthestNode(e, edges));
        }
    }

    private static int getMinDisFarthestNode(int N, int[][] edges) {
        Map<Integer, List<Integer>> hmap = new HashMap<>();
        if(N==2)  return 1;

        for(int i=0;i<edges.length;i++) {
            if(hmap.containsKey(edges[i][0]-1)) {
                hmap.get(edges[i][0]-1).add(edges[i][1]-1);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(edges[i][1]-1);
                hmap.put(edges[i][0]-1, temp);
            }
            if(hmap.containsKey(edges[i][1]-1)) {
                hmap.get(edges[i][1]-1).add(edges[i][0]-1);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(edges[i][0]-1);
                hmap.put(edges[i][1]-1, temp);
            }
        }

        boolean[] visited = new boolean[N];
        int[] maxLeafDist = new int[N];

        for(int i=0;i<N;i++) {
            if(hmap.get(i).size() == 1) {
                recurr(hmap, visited, maxLeafDist, i, 0);
                visited = new boolean[N];
            }
        }

        int result=Integer.MAX_VALUE;
        for(int i=0;i<maxLeafDist.length;i++) {
            result = Math.min(result, maxLeafDist[i]);
        }
        return result;
    }

    private static void recurr(Map<Integer, List<Integer>> hmap, boolean[] visited, int[] maxLeafDist, int node, int level) {
        maxLeafDist[node] = Math.max(maxLeafDist[node], level);

        for(int n:hmap.get(node)) {
            if(!visited[n]) {
                visited[n] = true;
                recurr(hmap, visited, maxLeafDist, n, level+1);
            }
        }
    }

}
