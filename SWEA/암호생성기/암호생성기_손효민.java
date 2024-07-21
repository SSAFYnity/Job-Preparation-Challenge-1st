import java.io.*;
import java.util.*;
 
public class Solution { 
 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // int T = 10;
         
        for(int t = 1; t<= 10 ; t++) {
            Queue<Integer> q = new LinkedList<Integer>();
            int N = Integer.parseInt(br.readLine()); //테스트케잉스의 번호
             
            //일단 전부 입력받음
            String[] in = br.readLine().split(" ");
            for(int i=0;i<8;i++) {
                q.offer(Integer.parseInt(in[i]));
            }
             
            boolean zero = false;
             
            while(!zero) { //숫자가 0보다 작아지는 경우 0으로 유지됨. while문 종료.
                 
                for(int i=1;i<=5;i++) {
                    //==> 이 과정을 5번씩 반복함 cnt가 1빼지고 
                    //다음 첫 번째 수는 2 감소한 뒤 맨 뒤로, 그 다음 첫 번째 수는 3을 감소하고 맨 뒤로, 그 다음 수는 4, 그 다음 수는 5를 감소한다.
                    int number = q.poll(); //맨 앞에 데이터 추출 후 삭제
                    int modifyNumber = number - i;
                    if(modifyNumber > 0) q.offer(modifyNumber); //맨 앞에는 cnt만큼 빼고 다시 cnt 증가시킨다음 큐에 넣어줌.
                    else {//if(modifyNumber <= 0) {
                        zero = true;
                        q.offer(0);
                        break;
                    }
//                  System.out.println(modifyNumber);
                }
            }
             
            StringBuilder sb = new StringBuilder();
            sb.append("#"+N);
            for(int i=0;i<8;i++) {
                sb.append(" "+q.poll());
            }
            System.out.println(sb);
        }
    }
}
