import java.util.*;
import java.io.*;


public class Main
{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws IOException
    {
        int n, k;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] input = new int[n];
        for (int i = 0; i < n; i++) input[i] = Integer.parseInt(st.nextToken());

        int[] numCnt = new int[100_001];
        int maxLen = 0;
        int right = 0;

        for (int left = 0; left < n; left++) {

            while (right < n) {
                int element = input[right];
                if (numCnt[element] >= k) break;
                numCnt[element]++;
                right++;
            }
            maxLen = Math.max(maxLen, right - left);
            numCnt[input[left]]--;
        }

        System.out.println(maxLen);

    }
}