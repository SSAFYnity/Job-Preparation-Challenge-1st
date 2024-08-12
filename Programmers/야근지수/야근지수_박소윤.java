import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.offer(work);
        }
        while(n > 0) {
            int cur = pq.poll();
            cur = Math.max(0, cur - 1);
            pq.offer(cur);
            n--;
        }
        long answer = 0;
        for (int work: pq) {
            answer += (long) work * work;
        }
        return answer;
    }
}