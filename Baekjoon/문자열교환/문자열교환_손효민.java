import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int len=0; // 윈도우의 크기
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='a') len++;
		}
		
		int min=Integer.MAX_VALUE;
		for(int i=0;i<str.length();i++) {
			int cnt=0; // b의 개수
			
			for(int j=i;j<=i+len-1;j++) {
				int tmp = j;
				if(j >= str.length()) tmp = j-str.length(); //처음과 끝은 서로 인접
        
				if(str.charAt(tmp)=='b') cnt++;
			}
			min = Math.min(min, cnt);
		}
		System.out.println(min);
	}
}
