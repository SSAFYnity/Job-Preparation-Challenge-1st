package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = 0;
        StringBuilder sb = new StringBuilder();
        Queue<Integer> que = new ArrayDeque<>();

        while(++TC <= 10){
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                que.offer(Integer.parseInt(st.nextToken()));
            }

            int offset = 0;
            int curr = 1;

            while(curr > 0){
                curr = que.poll() - ++offset;
                offset %= 5;
                que.offer(curr);
            }

            sb.append('#').append(TC).append(' ');
            while(!que.isEmpty()){
                sb.append( Math.max(que.poll(), 0) ).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
