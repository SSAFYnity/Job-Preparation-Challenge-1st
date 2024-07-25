import java.util.*;
import java.io.*;

class Solution {

	public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 1;
    StringBuilder sb = new StringBuilder();

		while(br.readLine() != null) {
    
      StringTokenizer st = new StringTokenizer(br.readLine());
      Queue<Integer> q = new ArrayDeque<Integer>();

      while(st.hasMoreTokens()) {
        q.offer(Integer.parseInt(st.nextToken()));
      }
      while(cycle(q)) {}
      
      sb.append("#").append(T++).append(" ");
      while(!q.isEmpty()) {
        sb.append(q.poll()).append(" ");
      }
      sb.append("\n");
		}
    System.out.println(sb);
	}

  private static boolean cycle(Queue<Integer> q) {
    for (int i = 1; i <= 5; i++) {
      int n = q.poll() - i;
      if (n <= 0) {
        n = 0;
        q.offer(n);
        return false;
      }
      q.offer(n);
    }
    return true;
  }
}