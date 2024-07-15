import java.io.*;
import java.util.*;

public class Solution {


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            sb.append("#").append(t).append(" ");
            String[] sa = bf.readLine().split(" ");
            int N = Integer.parseInt(sa[1]);
            List<Integer>[] graph = new List[100];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }

            sa = bf.readLine().split(" ");
            for (int i = 0; i < N * 2; i += 2) {
                int start = Integer.parseInt(sa[i]);
                int end = Integer.parseInt(sa[i + 1]);

                graph[start].add(end);
            }

            boolean result = bfs(graph);
            if(result) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }

        System.out.println(sb);
    }

    private static boolean bfs(List<Integer>[] graph){
        boolean[] visited = new boolean[100];
        Queue<Integer> q = new LinkedList<>();
        visited[0] = true;
        q.add(0);

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i=0; i<graph[cur].size(); i++){
                int next = graph[cur].get(i);
                // 99면 true 리턴
                if(next == 99) return true;
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
            }
        }

        // 안되면 false 리턴
        return false;
    }
}
