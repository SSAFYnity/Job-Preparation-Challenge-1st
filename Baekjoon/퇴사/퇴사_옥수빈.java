import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] chaArr = br.readLine().toCharArray();
		int answer = 0;
		String s = "";
		boolean plus = true;

		for (char c : chaArr) {
			if (c != '+' && c != '-') {
				s += c;
			} else {
				// 아직 마이너스가 나오지 않았으면 더해준다
				if(plus)
					answer += Integer.parseInt(s);
				// 마이너스가 나왔으면 빼준다
				else
					answer -= Integer.parseInt(s);
				// - 가 나온 경우 뒤의 수를 부호와 상관없이 모두 뺄 수 있으므로 plus를 false로 변경
				if (c == '-')
					plus = false;
				s = "";
			}
		}
		
		// 마지막 숫자 더해주기
		if(plus)
			answer += Integer.parseInt(s);
		else
			answer -= Integer.parseInt(s);
		
		System.out.println(answer);
	}

}