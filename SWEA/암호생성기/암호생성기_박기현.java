import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int[] cycle = {1, 2, 3, 4, 5};
    static StringBuilder sb = new StringBuilder();
    static Queue<Integer> q = new LinkedList<>();
 
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
 
        for(int t=1; t<=10; t++){
            bf.readLine();
            sb.append("#").append(t).append(" ");
            q.clear();
 
            String[] sa = bf.readLine().split(" ");
            for(int i=0; i<8; i++){
                q.add(Integer.parseInt(sa[i]));
            }
 
            cycling();
            for(int i=0; i<8; i++){
                sb.append(q.poll()).append(" ");
            }
            sb.append("\n");
        }
 
        System.out.println(sb);
    }
 
    private static void cycling(){
        int num = 0;
        while(true){
            num %= 5;
            if(q.peek() - cycle[num] <= 0){
                q.poll();
                q.add(0);
                break;
            }
            q.add(q.poll() - cycle[num++]);
        }
    }
}