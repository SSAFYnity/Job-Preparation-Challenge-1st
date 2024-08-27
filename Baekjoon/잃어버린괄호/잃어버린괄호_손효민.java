import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 뺄셈으로 나눠줌
		String[] str = br.readLine().split("-");
		int ans = 0; //뺄셈 할 수들의 결과
		for(int i = 0; i < str.length; i++) {
			int x = 0; //덧셈 할 수들의 결과
			
			if(str[i].contains("+")) {
				//+로 나눔
				String[] add = str[i].split("\\+");
				//  java.util.regex.PatternSyntaxException : split은 정규식으로 구분자를 받아서 몇몇의 특수문자는 \\를 붙여줘야함
				// replace, replaceAll, split에 해당함 -> | ? * ( ) [ ] { } \
				for(int j = 0; j< add.length;j++) {
					x += Integer.parseInt(add[j]);
					//+가 포함되어 있으면 +단위로 자르고 수들을 더해줌
				}
			}
			else {
				//숫자를 변환해줌
				x += Integer.parseInt(str[i]);
			}
			
			if(i == 0) ans += x; //첫번째 수는 무조건 더해줘야함
			else ans -= x; //나머지 수는 빼줘야함
		}
		
		System.out.println(ans);		
		
	}
}
