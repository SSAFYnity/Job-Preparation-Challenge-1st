import java.io.*;
import java.util.Queue;
import java.util.ArrayDeque;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int t=1; t<=10; t++) {		// 10개의 테스트케이스를 받기
            Queue<Integer> que = new ArrayDeque();      // 먼저 들어온 값이 먼저 나가도록 큐 사용
            int cycle = 0;      // 현재 진행된 암호 생성을 위한 반복 횟수
            int tc = Integer.parseInt(br.readLine());		// 현재 테스트케이스 번호
            String[] arr = br.readLine().split(" ");

            for(int i=0; i<8; i++) {
                int num = Integer.parseInt(arr[i]);
                que.add(num);       // 큐에 입력으로 주어진 8개의 숫자를 순서대로 넣기
            }

            while(true) {
                int num = que.poll();           // 큐에서 맨앞에 있는 값 뽑기
                if(num - (cycle % 5) - 1 <= 0 ) {       // 0이하인 값이 나오면 이 배열이 암호가 됨
                    que.add(0);     // 큐의 마지막에 0을 추가
                    break;
                }
                que.add(num - (cycle % 5) - 1);     // 1~5만큼 사이클에 따라서 감소시킨 값을 큐에 추가
                cycle++;        // 사이클 증가
            }

            /*
                테케별로 정답을 BufferedWriter에 넣기
             */
            bw.write("#" + tc + " ");
            for(int i=0; i<8; i++) {
                bw.write(que.poll() + " ");
            }
            bw.write("\n");
        }
        bw.flush();     // 10개의 테스트케이스데 대한 정답 출력
    }
}