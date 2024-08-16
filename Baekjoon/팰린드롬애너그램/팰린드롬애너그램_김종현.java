import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cnt = new int[26];
        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            cnt[str.charAt(i) - 'a']++;
        }

        if (N % 2 == 1) cnt[str.charAt(N / 2) - 'a']--;


        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}
