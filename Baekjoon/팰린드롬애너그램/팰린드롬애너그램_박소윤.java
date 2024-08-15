import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] alphabets = new int[26];
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        for (int i = 0; i < n; i++) {
            alphabets[str.charAt(i) - 'a']++;
        }
        if (n % 2 == 1) {
            alphabets[str.charAt(n / 2) - 'a']--;
        }
        for (int i = 0; i < alphabets.length; i++) {
            if (alphabets[i] % 2 == 1) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}