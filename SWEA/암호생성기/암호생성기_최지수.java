import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String args[]) throws Exception	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int t = 1; t <= 10; t++) {
            br.readLine();
            st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();
            sb.append("#");
            sb.append(t);
            sb.append(" ");

            int[] nums = new int[8];

            for (int i = 0; i < 8; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            int idx = 0;
            while (true) {
                nums[idx%8] -= idx%5+1;
                if (nums[idx%8] <= 0) {
                    nums[idx%8] = 0;
                    break;
                }
                idx++;
            }
            for (int i = idx+1; i < idx+9; i++) {
                sb.append(nums[i%8]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}
