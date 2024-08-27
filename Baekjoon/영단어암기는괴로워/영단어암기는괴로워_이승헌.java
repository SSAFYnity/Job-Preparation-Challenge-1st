package 영단어암기는괴로워;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 영단어암기는괴로워_이승헌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Node> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int len = str.length();
            if (len < M) {
                continue;
            }

            Node node = map.getOrDefault(str, new Node(str, 0, len));
            node.num++;
            map.put(str, node);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(map.values());

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll().str).append('\n');
        }
        System.out.println(sb);

    }

    private static class Node implements Comparable<Node> {
        String str;
        int num;
        int size;

        public Node(String str, int num, int size) {
            this.str = str;
            this.num = num;
            this.size = size;
        }

        @Override
        public int compareTo(Node o) {
            if (this.num == o.num) {
                if (this.size == o.size) {
                    return String.CASE_INSENSITIVE_ORDER.compare(this.str, o.str);
                }
                return o.size - this.size;
            }
            return o.num - this.num;
        }
    }
}
