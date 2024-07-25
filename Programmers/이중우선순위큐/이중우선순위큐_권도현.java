import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
        PriorityQueue<Integer> min = new PriorityQueue();
        PriorityQueue<Integer> max = new PriorityQueue(Collections.reverseOrder());
        HashMap<Integer, Integer> info = new HashMap<>();

        for (String s : operations) {
            String[] part = s.split(" ");
            String command = part[0];
            int num = Integer.parseInt(part[1]);
            if (command.equals("I")) {
                min.add(num);
                max.add(num);
                info.put(num, info.getOrDefault(num, 0) + 1);
            } else {
                if (num == 1) {
                    while (!max.isEmpty() && info.get(max.peek()) == 0) {
                        max.poll();
                    }
                    if (!max.isEmpty()) {
                        int temp = max.poll();
                        info.put(temp, info.get(temp) - 1);
                    }
                } else if (num == -1) {
                    while (!min.isEmpty() && info.get(min.peek()) == 0) {
                        min.poll();
                    }
                    if (!min.isEmpty()) {
                        int temp = min.poll();
                        info.put(temp, info.get(temp) - 1);
                    }
                }
            }
        }

        while(!max.isEmpty() && info.get(max.peek()) == 0){
            max.poll();
        }
        while(!min.isEmpty() && info.get(min.peek()) == 0){
            min.poll();
        }

        if (!max.isEmpty() && !min.isEmpty()) {
            answer[0] = max.peek();
            answer[1] = min.peek();
        }

        return answer;
    }
}