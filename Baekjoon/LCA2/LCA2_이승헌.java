package LCA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LCA2_이승헌 {

    static Edge[] edges;
    static int[][] parents;
    static int[] depths;

    static int N;
    static int treeHeight;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        edges = new Edge[N + 1];
        depths = new int[N + 1];


        for (int i = 1; i <= N; i++) {
            edges[i] = new Edge(i, null);
        }

        for (int num = 0; num < N - 1; num++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            edges[first].next = new Edge(second, edges[first].next);
            edges[second].next = new Edge(first, edges[second].next);
        }
        treeHeight = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        parents = new int[N + 1][treeHeight];

        init(1, 1, 0);
        setParents();

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append(LCA(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
        }
        System.out.println(sb);
    }

    private static int LCA(int high, int low) {
        // second 가 더 깊
        if(depths[high] > depths[low]){
            int tmp = low;
            low = high;
            high = tmp;
        }

        // a = b 높이 맞추기
        for (int i = treeHeight - 1; i >= 0; i--) {
            if(Math.pow(2,i) <= depths[low] - depths[high]){
                low = parents[low][i];
            }
        }
        if(low == high){ return low; }


        for (int i = treeHeight - 1; i >= 0; i--) {
            if(parents[low][i] != parents[high][i]){
                low = parents[low][i];
                high = parents[high][i];
            }
        }
        return parents[low][0];
    }

    private static void init(int index, int height, int parent) {
        depths[index] = height;

        for (Edge next = edges[index].next; next != null; next = next.next) {
            if (next.index == parent) {
                continue;
            }
            parents[next.index][0] = index;
            init(next.index, height + 1, index);
        }
    }

    private static void setParents() {
        for (int heightIndex = 1; heightIndex < treeHeight; heightIndex++) {
            for (int nodeIndex = 1; nodeIndex < N + 1; nodeIndex++) {
                parents[nodeIndex][heightIndex] = parents[parents[nodeIndex][heightIndex - 1]][heightIndex - 1];
            }
        }
    }
    private static class Edge {
        int index;
        Edge next;

        public Edge(int index, Edge next) {
            this.index = index;
            this.next = next;
        }
    }
}
