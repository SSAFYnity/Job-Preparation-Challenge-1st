import java.util.Map;
import java.util.HashMap;

class 가장많이받은선물_강민정 {
    public static int solution(String[] friends, String[] gifts) {
        int answer = 0;  // 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수
        int friendsLen = friends.length;  // 친구 수
        Map<String, Integer> idxMap = new HashMap<>();  // 친구 이름에 대한 인덱스 저장
        int[] giftDegree = new int[friendsLen];  // 선물지수 저장
        int[][] giftGraph = new int[friendsLen][friendsLen];  // 행: 선물을 준 사람, 열: 선물을 받은 사람

        // 친구 이름에 인덱스 할당
        for (int i = 0; i < friendsLen; i++) {
            idxMap.put(friends[i], i);
        }

        // 선물지수 계산 및 그래프 생성
        for (String gift : gifts) {
            String[] giftInfo = gift.split(" ");
            int giverIdx = idxMap.get(giftInfo[0]);
            int receiverIdx = idxMap.get(giftInfo[1]);

            giftDegree[giverIdx]++;
            giftDegree[receiverIdx]--;
            giftGraph[giverIdx][receiverIdx]++;
        }

        // 각 친구가 다음 달에 받을 선물 계산
        for (int i = 0; i < friendsLen; i++) {
            int num = 0;
            for (int j = 0; j < friendsLen; j++) {
                if (i == j) {
                    continue;
                }

                // 선물을 더 많이 주었거나, 선물 횟수가 같을 때 선물지수가 높은 경우
                if (giftGraph[i][j] > giftGraph[j][i] ||
                        (giftGraph[i][j] == giftGraph[j][i] && giftDegree[i] > giftDegree[j])) {
                    num++;
                }
            }
            // 최대값 갱신
            answer = Math.max(answer, num);
        }

        return answer;  // 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수
    }
}
