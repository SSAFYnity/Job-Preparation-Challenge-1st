import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<T;i++) {
			int N = Integer.parseInt(br.readLine());
			
			long[] arr = new long[101]; // i개의 성냥개비를 사용해서 만들 수 있는 가장 작은 수를 저장하는 배열
			Arrays.fill(arr, Long.MAX_VALUE);
			arr[2] = 1;
			arr[3] = 7;
			arr[4] = 4;
			arr[5] = 2; // 2, 3
			arr[6] = 6; //9, 0
			arr[7] = 8;
			arr[8] = 10; //9-> arr[9-2]+add[2-2] = arr[7]+add[0] = 81
			
			String[] add = { "1", "7", "4", "2", "0", "8" }; // 2,3,4,5,6,7개로 만든 가장 작은 숫자들. 
			
			for (int j = 9; j <= 100; j++) {
                for (int k = 2; k <= 7; k++) {
                    String line = "" + arr[j - k] + add[k - 2];
                    arr[j] = Math.min(Long.parseLong(line), arr[j]);
                }
            }
			
			/* 2. 가장 큰 수
			홀수 개 -> 홀수로 만들 수 있는 숫자(7), 나머지는 1
			짝수 개 -> 모두 1
			*/
			StringBuilder max = new StringBuilder();
            long a = N / 2;
            long b = N % 2;
            
            if (b == 1) {
                max.append("7");
            }else {
                max.append("1");
            }

            for(int j = 1; j < a; j++) {
                max.append("1");
            }
			
			sb.append(arr[N]).append(" ").append(max.toString()).append("\n");
		}
		System.out.println(sb.toString());
	}

}
