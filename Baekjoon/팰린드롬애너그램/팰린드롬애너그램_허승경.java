import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int check [] = new int[26];
        int mid = n/2;

        for(int i = 0; i < n; i++){
            check[str.charAt(i) - 'a'] += 1;
        }

        if(n%2 == 1) {
            check[str.charAt(n/2) - 'a'] -= 1;
        }

        String result = "Yes";
        for(int i = 0; i < 26; i++){
            if(check[i] % 2 == 1){
                result = "No";
                break;
            }
        }

        System.out.print(result);

    }
}
