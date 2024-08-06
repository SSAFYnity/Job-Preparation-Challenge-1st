import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] arr = new int [N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(arr[N-1][i]);
        }

        for(int i = N-2; i>=0; i--){
            for(int j = 0; j< N; j++){
                if(pq.peek() < arr[i][j]) {
                    pq.poll();
                    pq.add(arr[i][j]);
                }
            }
        }

        System.out.println(pq.peek());
    }
}