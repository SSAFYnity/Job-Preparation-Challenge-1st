import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        char[] chars = br.readLine().toCharArray();
        int[] arr = new int[26];

        for (char c : chars) {
            arr[c - 'a']++;
        }

        if (N % 2 == 1) {
            arr[chars[N / 2] - 'a']--;
        }

        boolean flag = true;
        for (int i : arr) {
            if (i % 2 != 0) {
                flag = false;
                break;
            }
        }

        if (flag) {
            bw.write("Yes");
        } else {
            bw.write("No");
        }
        bw.flush();
    }
}