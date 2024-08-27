import java.util.*;
import java.io.*;

public class Main {

    public static class Talk implements Comparable<Talk> {
        int start, end, earn;
        public Talk (int start, int end, int earn) {
            this.start = start;
            this.end = end;
            this.earn = earn;
        }
        public int compareTo(Talk o) {
            if (o.earn == this.earn) return o.start - this.start;
            return o.earn - this.earn;
        }
        public String toString() {
            return "[" + start + ", " + end + ", " + earn + "]";
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Map<Integer, TreeSet<Talk>> map = new HashMap<>();
        int[] dp = new int[n+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = i+1;
            int end = i + Integer.parseInt(st.nextToken());
            int earn = Integer.parseInt(st.nextToken());
            map.putIfAbsent(end, new TreeSet<>());
            map.get(end).add(new Talk(start, end, earn));
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i-1];
            if (!map.containsKey(i)) continue;
            for (Talk now : map.get(i)) {
                if (now.start <= 0) continue;
                dp[i] = Math.max(dp[now.start-1] + now.earn, dp[i]);
            }
        }

        System.out.println(dp[n]);
    }
}

