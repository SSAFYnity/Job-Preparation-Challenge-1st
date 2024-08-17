import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(br.readLine());
            int result = 1 + (num / 2) + (num / 3);
            for (int j = 3; j < num; j += 3) {
                result += (num - j) / 2;
            }
            answer.append(result).append("\n");
        }
        System.out.println(answer);
    }
}
