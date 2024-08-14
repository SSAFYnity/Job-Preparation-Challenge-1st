import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < works.length; i++){
            pq.offer((long) works[i]);
        }

        for(int i = 0; i < n; i++){
            if(pq.size() == 0) break;

            long tmp = pq.poll();
            if(--tmp > 0) pq.offer(tmp);
        }

        if(pq.size() == 0) answer = 0;
        else{
            while(!pq.isEmpty()){
                long num = pq.poll();
                answer += num * num;
            }
        }
        return answer;
    }
}