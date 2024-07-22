import java.util.*;
import java.io.*;
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            br.readLine();
            String[] datas = br.readLine().split(" ");
            int N = 8;
            Deque<Integer> password = new ArrayDeque<Integer>();
            for(int i=0;i<N;i++) {
                password.add(Integer.parseInt(datas[i]));
            }
            sb.append("#" + Integer.toString(test_case) + " ");
            int data = password.pop();
            int count = 1;
            while(data-count>0) {
                password.add(data-count++);
                data = password.pop();
                count = (count-1)%5 + 1;
            }
            while(!password.isEmpty()) {
                sb.append(Integer.toString(password.pop()) + " ");
            }
            sb.append("0\n");
        }
        System.out.println(sb);
    }
}