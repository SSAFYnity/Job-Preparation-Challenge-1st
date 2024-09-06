import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String strInput = br.readLine();
    String[] input = strInput.split("-");

    int answer = 0;
    for (int i = 0; i < input.length; i++) {
      String[] cur = input[i].split("\\+");

      int tmp = 0;
      for (int j = 0; j < cur.length; j++) {
        if (cur[j].equals("+"))
          continue;
        tmp += Integer.parseInt(cur[j]);
      }
      if (i == 0) {
        answer = tmp;
      } else {
        answer -= tmp;
      }
    }

    System.out.println(answer);
  }
}
