import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		int aCnt = 0;
		for (char c : str.toCharArray()) {
			if (c=='a') aCnt++;
		}
		/*
		1. a 총 길이만큼 문자열 잘라서 비교하기
		2. 비교해서 b 수가 가장 적은것 갱신
		3. 인덱스 시작점이 문자열 마지막까지
		 */
		int start = 0, bCnt =0, end = aCnt-1;
		for (int i = 0; i < aCnt; i++) {
			if (str.charAt(i) == 'b') bCnt++;
		}
		int answer = bCnt;
		int len = str.length();

		while (start < len) {
			// 이전 시작위치값이 b인 경우 -1
			if(str.charAt(start++) == 'b') bCnt--;
			// 다음 종료위치값이 b인 경우 +1
			// 나머지연산을 하게되면 end가 문자열 넘어도 인덱스가 처음부터 이동하는것과 같음
			if(str.charAt(++end % len) == 'b') bCnt++;
			answer = Math.min(answer, bCnt);
		}

		System.out.println(answer);
	}

}
