import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split_minus = br.readLine().split("-");

        int answer = 0;

        for (int i = 0; i < split_minus.length; i++) {
            String[] split_plus = split_minus[i].split("[+]");

            int sum = 0;
            for (String s : split_plus) {
                sum += Integer.parseInt(s);
            }
            if (i == 0)
                answer += sum;
            else
                answer -= sum;
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}