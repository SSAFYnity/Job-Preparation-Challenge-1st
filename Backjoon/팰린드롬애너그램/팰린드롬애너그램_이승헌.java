package 팰린드롬애너그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬애너그램_이승헌 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long bit = 0;
        String input = br.readLine();

        if(N % 2 != 0){
            bit |= 1L << input.charAt(N >> 1);
        }

        for (int idx = 0; idx < N; idx++) {
            bit ^= (1L << input.charAt(idx));
        }
        System.out.println(bit == 0 ? "Yes" : "No");
    }
}
