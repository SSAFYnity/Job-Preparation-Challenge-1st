import java.util.;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueueInteger que = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        
        for (int i  works) {
            que.add(i);
        }
        
        while (n  0) {
            int a = que.poll();
            if (a == 0) break;
            que.add(a-1);
            n--;
        }
        
        while (!que.isEmpty()) {
            int a = que.poll();
            if (a == 0) break;
            answer += a  a;
        }
        
        return answer  0  0  answer;
    }
}