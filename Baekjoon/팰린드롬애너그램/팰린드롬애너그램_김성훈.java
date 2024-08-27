import java.util.*;
import java.io.*;


public class Main
{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws IOException
    {

        int aToZ = 'z' - 'a' + 1;

        int[] count = new int[aToZ];

        int n = Integer.parseInt(br.readLine());
        int half = n / 2;
        char[] str = br.readLine().toCharArray();

        for (int i = 0; i < half; i++) {
            int left = str[i] - 'a';
            int right = str[n - i - 1] - 'a';

            if (left == right) continue;
            count[left]++;
            count[right]++;
        }

        boolean flag = true;
        for (int i = 0; i < aToZ; i++) {
            if (count[i] % 2 == 0) continue;
            flag = false;
            break;
        }

        System.out.println(flag ? "Yes" : "No");
    }
}