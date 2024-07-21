import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
         
        for(int t=1; t<=10; t++) {
            // 입력
            int testNum = Integer.parseInt(br.readLine());
            String[] numbers = br.readLine().split(" ");
             
            int[] passKey = new int[8];
            Queue<Integer> queue = new ArrayDeque<Integer>();
            for(int i=0; i<8; i++) {
                passKey[i] = Integer.parseInt(numbers[i]);
                queue.offer(passKey[i]);
            }
             
            // 암호 생성
            int num = 1;
            boolean flag = false;
             
            while(!flag) {
                // 첫 번째 수를 num만큼 감소 후, 맨 뒤로 이동
                int peek = queue.poll() - num;
                if(peek < 0) peek = 0;
                queue.offer(peek);
                 
                // 0으로 저장되는 경우 프로그램 종료
                if(peek==0) flag = true;
                 
                // num 업데이트
                num = (num==5) ? 1 : num+1;             
            }
             
            // 결과 저장
            sb.append(String.format("#%d",testNum));
            while(!queue.isEmpty()) {
                sb.append(String.format(" %d", queue.poll()));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
