import java.util.*;
import java.io.*;
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        int size = 8;
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            sb.append("#" + br.readLine() + " ");
            int[] remainder = new int[size];
            String[] datas = br.readLine().split(" ");
            int temp = Integer.parseInt(datas[0]);
            remainder[0] = temp%15;
            int q = temp/15;
            if(remainder[0]==0) {
                q--;
                remainder[0] = 15;
            }
            boolean checkMinus = false;
            for(int i=1;i<size;i++) {
                remainder[i] = Integer.parseInt(datas[i]) - q*15;
                if(remainder[i]<=0) {
                    checkMinus = true;
                }
            }
            if(checkMinus) {
                for(int i=0;i<size;i++) {
                    remainder[i] += 15;
                }
            }
            int startIndex = 0;
            for(int i=1;;i++) {
                int numberIndex = (i-1)%size;
                remainder[numberIndex] -= (i-1)%5 + 1;
                if(remainder[numberIndex]<=0) {
                    remainder[numberIndex]=0;
                    startIndex= numberIndex + 1;
                    break;
                }
            }
            for(int i=startIndex;i<startIndex+size;i++) {
                sb.append(remainder[i%size] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}