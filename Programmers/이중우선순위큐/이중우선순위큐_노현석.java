import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

        for (String ele : operations) {
            StringTokenizer st = new StringTokenizer(ele);
            String judge = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            if (pq.size() < 1 && judge.equals("D"))
                continue;

            if (judge.equals("I")) {
                pq.offer(value);
                maxPq.offer(value);
                continue;
            }

            if(value < 0) {
                int min = pq.poll();
                maxPq.remove(min);
                continue;
            }

            int max = maxPq.poll();
            pq.remove(max);
        }
        if(pq.size() > 0 ) {
            answer[0] = maxPq.poll();
            answer[1] = pq.poll();
        }
        return answer;
    }
}