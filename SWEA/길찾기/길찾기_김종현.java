import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution {
    private static boolean visited[], check;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = 10;
 
        StringBuilder sb = new StringBuilder();
        while (--T >= 0) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(stk.nextToken());
            int N = Integer.parseInt(stk.nextToken());
 
            stk = new StringTokenizer(br.readLine());
            List<Integer> list[] = new ArrayList[100 + 1];
            for (int i = 0; i < N; i++) {
                int n1 = Integer.parseInt(stk.nextToken());
                int n2 = Integer.parseInt(stk.nextToken());
                if (list[n1] == null) list[n1] = new ArrayList<>();
 
                list[n1].add(n2);
            }
 
            check = false;
            Queue<Integer> q = new LinkedList<>();
 
            q.add(0);
 
            int ret = 0;
            while (!q.isEmpty()) {
                int curr = q.poll();
 
                if (curr == 99) {
                    ret++;
                    break;
                }
                if (list[curr] == null) continue;
 
                for (int next : list[curr]) {
                    q.add(next);
                }
            }
            sb.append("#" + id).append(" ").append(ret).append("\n");
        }
 
        System.out.print(sb.toString());
    }
}