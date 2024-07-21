import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();

        for (String operation : operations) {
            String[] temp = operation.split(" ");
            char cmd = temp[0].charAt(0);
            int num = Integer.parseInt(temp[1]);
            switch (cmd) {
                case 'I':
                    maxPQ.offer(num);
                    minPQ.offer(num);
                    if (map.containsKey(num)) {
                        map.put(num, map.get(num) + 1);
                    }
                    else {
                        map.put(num, 1);
                    }
                    break;
                case 'D':
                    if (num == 1) {
                        checkPQ(maxPQ, map, false);
                    }
                    else {
                        checkPQ(minPQ, map, false);
                    }
                    break;
            }
        }

        int[] answer = { checkPQ(maxPQ, map, true), checkPQ(minPQ, map, true)};
        return answer;
    }

    int checkPQ(PriorityQueue<Integer> pq, Map<Integer, Integer> map, boolean searchMode) {
        while (!pq.isEmpty() && !map.containsKey(pq.peek())) {
            pq.poll();
        }

        if (pq.isEmpty()) {
            return 0;
        }

        if (!searchMode) {
            int next = pq.poll();
            if (map.get(next) == 1) {
                map.remove(next);
            } else {
                map.put(next, map.get(next) - 1);
            }
            return next;
        }
        else {
            return pq.peek();
        }
    }
}