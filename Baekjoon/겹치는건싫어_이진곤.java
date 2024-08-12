import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 겹치는건싫어_이진곤 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        int answer = 1;
        int start = -1;

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (!map.containsKey(num)) {
                map.put(num, new LinkedList<>());
            }
            map.get(num).offer(i);
            if (map.get(num).size() > K) {
                start = Math.max(start, map.get(num).poll());
            }
            answer = Math.max(answer, i - start);
        }
        System.out.println(answer);
    }
}