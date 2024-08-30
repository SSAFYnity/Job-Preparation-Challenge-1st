import java.util.*;

class Solution {
    
    private Map<String, Map<String, Integer>> graph = new HashMap<>();
    private Map<String, int[]> giftRates = new HashMap<>();
    
    public int solution(String[] friends, String[] gifts) {
        // 그래프 초기화
        for (String friend : friends) {
            graph.put(friend, new HashMap<>());
            giftRates.put(friend, new int[3]);
        }
        for (Map<String, Integer> map : graph.values()) {
            for (String friend : friends) {
                map.put(friend, 0);
            }
        }
        // 선물 주고받는 관계 그래프 채우기
        for (String gift : gifts) {
            StringTokenizer st = new StringTokenizer(gift);
            String from = st.nextToken();
            String to = st.nextToken();
            int n = graph.get(from).get(to);
            graph.get(from).merge(to, 1, Integer::sum);
            giftRates.get(from)[0]++;
            giftRates.get(to)[1]++;
        }
        // 친구들 별 선물지수 구하기
        for (int[] data : giftRates.values()) {
            data[2] = data[0] - data[1];
        }        
        int answer = 0;
        // 가장 많은 선물을 받는 친구가 받을 선물의 수 구하기
        for (String from : friends) {
            int sum = 0;
            for (String to: friends) {
                int give = graph.get(from).get(to);
                int take = graph.get(to).get(from);
                if (give > take) {
                    sum++;   
                } else if (give == take) {
                    int fromGiftRate = giftRates.get(from)[2];
                    int toGiftRate = giftRates.get(to)[2];
                    if (fromGiftRate > toGiftRate) {
                        sum++;
                    }
                }
            }
            answer = Math.max(answer, sum);        
        }
        return answer;
    }
}