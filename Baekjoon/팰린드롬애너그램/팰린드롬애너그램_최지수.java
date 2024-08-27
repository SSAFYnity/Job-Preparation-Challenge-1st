import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        String str = br.readLine();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (n%2==1 && i == n/2) continue;
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0)+1);
        }

        String answer = "Yes";
        for (char c : map.keySet()) {
            if (map.get(c) % 2 == 1) {
                answer = "No";
                break;
            }
        }

        System.out.println(answer);
    }
}
