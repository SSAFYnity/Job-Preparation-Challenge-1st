import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String [] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String result = "";

        if (n % 2 == 0) {
            result = "CY";
        } else {
            result = "SK";
        }

        System.out.println(result);
    }
}