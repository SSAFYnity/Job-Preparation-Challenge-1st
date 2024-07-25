import java.util.*;

class Solution {

    public int solution(int[] picks, String[] minerals) {

        HashMap<String, Integer> mineralMap = new HashMap();
        mineralMap.put("diamond", 100000);
        mineralMap.put("iron", 1000);
        mineralMap.put("stone", 10);

        int size = minerals.length;
        int[] mineralToInt = new int[size];
        for (int i = 0; i < size; i++) {
            mineralToInt[i] = mineralMap.get(minerals[i]);
        }

        int[] tired = new int[(size + 4) / 5];

        for (int i = 0; i < tired.length; i++) {
            for (int j = 0; j < 5; j++) {
                if (i * 5 + j >= size) break;
                tired[i] -= mineralToInt[i * 5 + j];
            }
        }
        int[] tiredSort = tired.clone();
        Arrays.sort(tiredSort);

        int answer = 0;
        for (int i = 0; i < tired.length; i++) {
            if (picks[0] != 0) {
                picks[0]--;
                answer -= tired[i] / 100000;
                answer -= tired[i] % 100000 / 1000;
                answer -= tired[i] % 1000 / 10;
            } else if (picks[1] != 0) {
                picks[1]--;
                answer -= tired[i] / 100000 * 5;
                answer -= (tired[i] % 100000) / 1000;
                answer -= tired[i] % 1000 / 10;
            } else if (picks[2] > 0){
                picks[2]--;
                answer -= tired[i] / 100000 * 25;
                answer -= tired[i] % 100000 / 1000 * 5;
                answer -= tired[i] % 100000 % 1000 / 10;
            }
        }


        return answer;
    }
}