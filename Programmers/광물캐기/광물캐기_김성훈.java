import java.util.*;

class Solution {

    public int solution(int[] picks, String[] minerals) {

        HashMap<String, Integer> mineralMap = new HashMap();
        mineralMap.put("diamond", 100000);
        mineralMap.put("iron", 1000);
        mineralMap.put("stone", 10);

        int mineralSizes = minerals.length;
        int[] mineralToInt = new int[mineralSizes];
        for (int i = 0; i < mineralSizes; i++) {
            mineralToInt[i] = mineralMap.get(minerals[i]);
        }

        int pickSizes = picks[0] + picks[1] + picks[2];
        int[] tired = new int[pickSizes];

        for (int i = 0; i < pickSizes; i++) {
            for (int j = 0; j < 5; j++) {
                if (i * 5 + j >= mineralSizes) break;
                tired[i] -= mineralToInt[i * 5 + j];
            }
        }

        Arrays.sort(tired);

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
                answer -= tired[i] % 100000 / 1000;
                answer -= tired[i] % 1000 / 10;
            } else if (picks[2] != 0){
                picks[2]--;
                answer -= tired[i] / 100000 * 25;
                answer -= tired[i] % 100000 / 1000 * 5;
                answer -= tired[i] % 100000 % 1000 / 10;
            }
        }


        return answer;
    }
}