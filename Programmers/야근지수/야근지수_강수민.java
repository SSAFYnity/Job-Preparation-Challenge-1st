import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public long solution(int n, int[] works) {

        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());

        for(int i : works) {
            que.offer(i);
        }

        for(int i = 0; i < n; i++) {
            int cur = que.poll();
            if (cur == 0) break;
            que.offer(cur-1);
        }


        long answer = 0;

        for(int i : que) {
            answer += i * i;
        }

        return answer;
    }
}