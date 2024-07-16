import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class SWEA_1219_길찾기 {

    static int n, graph[][];
    static boolean v[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            n = Integer.parseInt(st.nextToken());

            graph = new int[2][100];
            v = new boolean[100];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(graph[0][a] == 0) {
                    graph[0][a] = b;
                } else {
                    graph[1][a] = b;
                }
            }
            boolean res = bfs();
            System.out.printf("%s%d %d\n", "#", tc, res ? 1 : 0);
        }
    }
    static boolean bfs() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        v[0] = true;

        while(!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < 2; i++) {
                int next = graph[i][now];
                if(next == 99) {
                    return true;
                }
                if(!v[next]) {
                    v[next] = true;
                    q.offer(next);
                }
            }
        }
        return false;
    }
}
