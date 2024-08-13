import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 1,2,3더하기4_이진곤 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        int[] arr = new int[10001];
        arr[1] = 1;
        for (int i = 2; i <= 10000; i++) {
            int two = 0;
            int newNum = 0;
            while (true) {
                int remained = i - 2 * two;
                if (remained < 0) {
                    break;
                }
                if (remained % 3 == 0) {
                    newNum++;
                }
                two++;
            }
            arr[i] = arr[i - 1] + newNum;
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int num = Integer.parseInt(in.readLine());
            sb.append(arr[num]).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}