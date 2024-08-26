import java.util.Map;
import java.util.HashMap;

class 가장많이받은선물_강민정 {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int friendsLen = friends.length;
        Map<String, Integer> idxMap = new HashMap();    // 2차원 배열에서 인덱스 저장 
        int[][] giftArr = new int[friendsLen][friendsLen];  // 행: 선물을 준 사람, 열: 선물을 받은 사람
        Map<Integer, Integer> giftValues = new HashMap();        // 선물지수 계산
        int idx = 0;
        int[] receiveArr = new int[friendsLen];          // 다음 달에 받을 선물의 수

        // 2차원 배열에서 인덱스 할당 및 선물지수 계산
        for(String gift : gifts) {
            String[] giftInfo = gift.split(" ");
            if(!idxMap.containsKey(giftInfo[0])) {
                idxMap.put(giftInfo[0], idx++);
                giftValues.put(idx - 1, 0);      // 친구에게 준 선물 수
            }
            if(!idxMap.containsKey(giftInfo[1])) {
                idxMap.put(giftInfo[1], idx++);
                giftValues.put(idx - 2, 0);      // 친구에게 받은 선물 수
            }

            giftValues.put(idx - 2, giftValues.getOrDefault(giftInfo[0], 0) + 1);       // 선물을 준 사람
            giftValues.put(idx - 1, giftValues.getOrDefault(giftInfo[1], 0) - 1);       // 선물을 받은 사람

            giftArr[idx - 2][idx - 1] += 1;     // 선물을 줌
            giftArr[idx - 1][idx - 2] += 1;     // 선물을 받음
        }

        System.out.println(giftValues);

        for(int i=0; i<friendsLen; i++) {
            for(int j=0; j<friendsLen; j++) {
                if(i == j) {        // 자기 자신은 건너띄기
                    continue;
                }
                if(giftArr[i][j] == 0 || giftArr[j][i] == 0 || (giftArr[i][j] == giftArr[j][i])) {  // 선물을 주고 받지 않았거나 주고 받은 수가 같다면
                    // 선물지수가 더 큰 사람이 선물을 받는다
                    if(giftValues.get(i) > giftValues.get(j)) {     // i가 선물지수가 더 클때
                        receiveArr[i] += 1;
                    } else if(giftValues.get(i) < giftValues.get(j)) {       // j가 선물지수가 더 작을 때
                        receiveArr[j] += 1;
                    }
                } else if(giftArr[i][j] > giftArr[j][i]) {      // i가 선물을 더 많이 줬을 때
                    receiveArr[j] += 1;
                } else if(giftArr[i][j] < giftArr[j][i]) {      // j가 선물을 더 많이 줬을 때
                    receiveArr[i] += 1;
                }
            }
        }
        /*
        for(int i=0; i<friendsLen; i++) {
            answer = Math.max(answer, receiveArr[i]);
        }
        */

        return answer;      // 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수
    }
}