import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((n1,n2) -> n2-n1);
        
        for(int i=0; i<works.length; i++) {
            pq.add(works[i]);
        }
        
        while(--n>=0) {
            int num = pq.poll();
            pq.add(num-1>0 ? num-1 : 0);
        }
        
        while(!pq.isEmpty()) {
            int num = pq.poll();
            answer += num * num;
        }
        
        return answer;
    }
}