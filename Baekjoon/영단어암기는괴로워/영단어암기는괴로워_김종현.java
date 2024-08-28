import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (str.length() < M) continue;
            m.compute(str, (k, v) -> v == null ? 1 : v + 1);
        }

        List<String> list = new ArrayList(m.keySet());
        Collections.sort(list, (s1,s2) -> {
            if(Integer.compare(m.get(s1), m.get(s2)) != 0) {
                return Integer.compare(m.get(s2), m.get(s1));
            }

            if(s1.length() != s2.length()) {
                return s2.length() - s1.length();
            }

            return s1.compareTo(s2);
        });

        StringBuffer sb = new StringBuffer();
        for(String s : list) {
            sb.append(s).append("\n");
        }

        System.out.print(sb.toString());
    }
}