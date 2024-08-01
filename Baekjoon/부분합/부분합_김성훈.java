import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, s;
    static int[] inputArr;

    static void input() throws IOException{

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        inputArr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            inputArr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro() {

        int minLength = Integer.MAX_VALUE;

        int right = 0, sum = 0;
        for (int left = 1; left <= n; left++) {

            sum -= inputArr[left - 1];
            while (sum < s && ++right <= n) {
                sum += inputArr[right];
            }

            if (sum < s) continue;
            minLength = Math.min(right - left + 1, minLength);
        }


        if (minLength == Integer.MAX_VALUE) minLength = 0;
        System.out.println(minLength);
    }

    public static void main(String[] args) throws IOException{
        input();
        pro();
    }
}