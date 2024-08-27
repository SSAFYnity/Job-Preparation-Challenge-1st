import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int w : works){
            pq.add(w);
        }
        
        for(int i=0;i<n;i++){
            if(!pq.isEmpty()){
                int x = pq.poll();
                if(x==0) break;
                pq.add(--x);
            }
        }
        
        long answer = 0;
        for(int x : pq){
            answer += x*x;
        }        
        return answer;
    }
}
