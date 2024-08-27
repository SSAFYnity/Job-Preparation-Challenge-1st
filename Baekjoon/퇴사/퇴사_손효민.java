import java.io.*;
import java.util.*;

public class Main {
	
	static int N, max=0; //최대 이익
	static int[][] tp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //퇴사일 하루전
		tp = new int[N][2]; //t:기간, p:금액
		for(int i=0;i<N;i++) { // 기간과 금액 입력받기
			st = new StringTokenizer(br.readLine());
			tp[i][0] = Integer.parseInt(st.nextToken());
			tp[i][1] = Integer.parseInt(st.nextToken());
		}		
		func(0,0);
		System.out.println(max);

	}
	
	public static void func(int i, int money) {
		if(i >= N) { //7일 이후가 되면 빼기
			max = Math.max(max, money);
			return;
		}
		//
		if(i+tp[i][0]<=N) {
			func(i+tp[i][0], money+tp[i][1]); // 순차적으로 다음날로 옮기는 것, max구하러 재귀 들어가기
		}
		else {
			func(i+tp[i][0], money); //만약에 지금 날짜로부터 할 수 있는 날 더했을 때 N일 초과된다면 money더하지 말고 일단 넘기기->재귀
		}
		//1일 부터 시작하는거 말고도 만들어주기 //순차적으로 가는 것 외에도 다른 방법이 있는지 찾아보는 아래의 코드
		func(i+1, money);
	}

}
