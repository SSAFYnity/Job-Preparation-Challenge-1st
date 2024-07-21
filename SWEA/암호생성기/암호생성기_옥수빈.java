import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            int T = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            Deque<Integer> deque = new ArrayDeque<>();
            for (String s : input) {
                deque.add(Integer.parseInt(s));
            }
            int idx = 1;
            while (true) {
                int num = deque.pollFirst() - idx++;
                if (idx == 6)
                    idx = 1;

                if (num <= 0)
                    num = 0;
                deque.addLast(num);
                if (num == 0)
                    break;
            }
            sb.append("#").append(t).append(" ");
            for(int d : deque){
                sb.append(d).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
	}
}