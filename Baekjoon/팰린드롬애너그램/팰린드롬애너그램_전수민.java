import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        HashMap<Character, Integer> map = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        for (int i = 0; i < (N/2); i++) {
            map.compute(s.charAt(i), (k, ov) -> map.get(k) == null? 1 : map.get(k)+1);
            map.compute(s.charAt(N-i-1), (k, ov) -> map.get(k) == null? 1 : map.get(k)+1);
        }

        for (char temp : map.keySet()){
            if (map.get(temp) % 2 != 0) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}