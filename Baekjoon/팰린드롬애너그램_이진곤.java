import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬애너그램_이진곤 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] alphabets = new int[26];
        int N = Integer.parseInt(in.readLine());
        String str = in.readLine();
        for (int i = 0; i < str.length(); i++) {
            if (str.length() % 2 != 0 && i == str.length() / 2) {
                continue;
            }
            alphabets[str.charAt(i) - 'a']++;
        }
        for (int alphabet : alphabets) {
            if (alphabet % 2 != 0) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}