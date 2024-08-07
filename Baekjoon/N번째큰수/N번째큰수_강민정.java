import java.io.*;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

class N번째큰수_강민정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());        // 1 ≤ N ≤ 1,500
        Queue<Integer> que = new PriorityQueue(Comparator.reverseOrder());      // 우선순위 큐 내림차순 정렬
        for(int i = 0; i<n; i++) {
            String[] secondInput = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                int num = Integer.parseInt(secondInput[j]);
                que.add(num);
            }
        }
        for(int i=0; i<n-1; i++) {
            que.remove();       // n-1개 제거
        }
        System.out.println(que.remove());       // n번째 큰 수 출력
    }
}
