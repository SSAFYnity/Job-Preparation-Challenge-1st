import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 암호생성기_노현석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            st = new StringTokenizer(br.readLine());
            ArrayDeque<Integer> Q = new ArrayDeque();
            for(int i=0; i<8; i++) {
                Q.offer(Integer.parseInt(st.nextToken()));
            }
            int idx = 1;
            while(true) {
                if(Q.peek()-idx <=0) {
                    Q.poll();
                    Q.offer(0);
                    break;
                }
                Q.offer(Q.poll()-idx);
                idx = (idx>=5) ? 1 : idx+1;
            }
            System.out.printf("#%d ", tc);
            for(int ele : Q) {
                System.out.print(ele+" ");
            }
            System.out.println();
        }

    }
}
