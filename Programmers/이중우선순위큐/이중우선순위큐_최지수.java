import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        TreeMap<Integer, Integer> que = new TreeMap<>();

        for (String str : operations) {
            StringTokenizer st = new StringTokenizer(str);
            char command = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            if (command == 'I') que.put(num, que.getOrDefault(num, 0)+1);
            else if (que.size() == 0) continue;
            else {
                int key = 0;
                if (num == 1) key = que.lastKey();
                else key = que.firstKey();

                if (que.get(key) == 1) que.remove(key);
                else que.put(key, que.get(key)-1);
            }
        }

        if (que.size() != 0) {
            answer[0] = que.lastKey();
            answer[1] = que.firstKey();
        }

        return answer;
    }
}