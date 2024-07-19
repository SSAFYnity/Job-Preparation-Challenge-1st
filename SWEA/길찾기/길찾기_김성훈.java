import java.util.*;
import java.io.*;

class Solution
{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception
    {
        for(int tc = 1; tc <= 10; tc++)
        {
            input();
            pro(tc);
        }
        System.out.println(sb);
    }

    static int n;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());

        st.nextToken();
        n = Integer.parseInt(st.nextToken());

        visit = new boolean[100];
        graph = new ArrayList[100];
        for (int i = 0; i < 100; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
        }
    }

    static void pro(int tc) {

        Deque<Integer> que = new ArrayDeque<>();

        que.add(0);
        visit[0] = true;

        while(!que.isEmpty()) {

            int cur = que.pollFirst();

            for (int next : graph[cur]) {
                if (visit[next]) continue;
                visit[next] = true;
                que.addFirst(next);
            }
        }


        sb.append('#').append(tc).append(' ');
        if (visit[99]) sb.append(1);
        else sb.append(0);
        sb.append('\n');
    }
}