import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static ArrayList<Integer>[] tree;
    static boolean[] visit;

    static int[] depth;
    static int maxDepth = 17; // log2(100,000) ~ 16.66
    static int[][] parent;

    static int n, m;

    static void input() throws IOException{
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree[start].add(end);
            tree[end].add(start);
        }
        visit = new boolean[n + 1];
        depth = new int[n + 1];
        parent = new int[maxDepth + 1][n + 1];
    }

    static void pro() throws IOException{

        dfs(1, 1); // 각각의 노드별 부모 계산 완료
        dp();

        m = Integer.parseInt(br.readLine());
        int a, b;
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int curDepth, int curValue) {

        visit[curValue] = true;
        depth[curValue] = curDepth;
        for (int next : tree[curValue]) {
            if (visit[next]) continue;
            parent[0][next] = curValue; // 2^0 -> 자식의 부모는 현재 값
            dfs(curDepth + 1, next);
        }
    }

    static void dp() {
        for (int i = 1; i < maxDepth; i++) { // 2^i번째 부모는 2^i-1번째
            for (int j = 1; j <= n; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
    }

    static int lca(int target, int compare) {

        if (target == 1 || compare == 1) return 1;

        if (depth[target] < depth[compare]) {
            int tmp = target;
            target = compare;
            compare = tmp;
        }

        if (depth[target] != depth[compare]) { // a가 무조건 depth가 큰 상태
            for (int i = maxDepth; i >= 0; i--) {
                if (depth[parent[i][target]] >= depth[compare]) {
                    target = parent[i][target];
                }
            }
        }

        int res = target;
        if (target != compare) {
            for (int i = maxDepth; i >= 0; i--) {
                if (parent[i][target] != parent[i][compare]) {
                    target = parent[i][target];
                    compare = parent[i][compare];
                }
                res = parent[i][target];
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException{
        input();
        pro();
    }
}