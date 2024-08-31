import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

class 주사위고르기_강민정 {
    static int halfDiceCnt;     // 총 주사위 갯수의 절반
    static int diceCnt;         // 2 <= 총 주사위 갯수 <= 10
    static int maxWin = 0;      // A의 최대 승률
    static int[][] diceInfo;    // 입력으로 주어진 주사위 배열
    static Set<Integer> set = new HashSet();        // A가 고른 주사위 조합
    static int[] answer;

    /*
        2 <= dice의 길이 = n <= 10
        n은 2의 배수
        dice[i]는 i+1번 주사위에 쓰인 6개의 수를 담음
        dice[i]의 길이 = 6
        1 <= dice[i]의 원소 <= 100
    */
    public int[] solution(int[][] dice) {
        diceInfo = dice;
        diceCnt = dice.length;      // 주사위 갯수
        halfDiceCnt = diceCnt / 2;  // 주사위 갯수의 절반
        answer = new int[halfDiceCnt];

        combination(0, 0);      // 조합 1개를 만들 때마다 A의 승률을 계산

        return answer;      // 승률이 가장 높은 주사위 번호를 오름차순 정렬
    }

    /*
        주사위를 n/2개 고르는 조합 생성 후 이분 탐색으로 A의 승률을 계산
        start: 조합에서 시작 인덱스
        cnt: 현재 고른 주사위 갯수
    */
    public void combination(int start, int cnt) {
        if(cnt == halfDiceCnt) {      // n/2만큼 다 뽑음
            List<Integer> aSum = new ArrayList();       // A가 던진 주사위의 합 리스트
            List<Integer> bSum = new ArrayList();       // B가 던진 주사위의 합 리스트
            List<Integer> aDice = new ArrayList(set);     // A가 고른 주사위의 조합
            List<Integer> bDice = new ArrayList();        // B가 고른 주사위의 조합
            calSum(0, 0, aSum, aDice);       // A가 고른 주사위의 합
            int win = 0;        // A의 승률

            // B가 고른 주사위 조합 구하기
            for(int i=0; i<diceCnt; i++) {
                if(set.contains(i)) {       // A가 이미 고른 주사위라면
                    continue;
                }
                bDice.add(i);       // B가 고른 주사위
            }

            calSum(0, 0, bSum, bDice);       // B가 고른 주사위의 합

            // 이분탐색을 하기 위해 b의 합 리스트를 오름차순 정렬
            Collections.sort(bSum);

            win = binarySearch(aSum, bSum);     // 이분탐색

            if(win > maxWin) {      // 승률이 더 높다면
                Collections.sort(aDice);    // A가 고른 주사위 조합을 오름차순 정렬
                maxWin = win;       // A의 승률 갱신
                // 주사위 조합을 갱신
                for(int x=0; x<halfDiceCnt; x++) {
                    answer[x] = aDice.get(x) + 1;       // 주사위 번호는 1번부터 시작함
                }
            }
            return;
        }

        for(int i=start; i<diceCnt; i++) {
            set.add(i);     // i번째 주사위 선택
            combination(i + 1, cnt + 1);
            set.remove(i);
        }
    }

    /*
        A또는 B가 고른 주사위의 합을 계산하기 위해 중복순열 사용
        sum: 주사위의 합
        cnt: 현재 던진 주사위의 수
        sumLst: 주사위를 다 던진 후 합을 저장할 리스트
        diceLst: 현재 고른 주사위들
    */
    public void calSum(int sum, int cnt, List<Integer> sumLst, List<Integer> diceLst) {
        if(cnt == halfDiceCnt) {    // 주사위를 다 던졌다면
            sumLst.add(sum);      // 합 저장
            return;
        }

        for(int i=0; i<6; i++) {        // 주사위는 6개의 면이 있음
            calSum(sum + diceInfo[diceLst.get(cnt)][i], cnt + 1, sumLst, diceLst);
        }
    }

    /*
        A의 합마다 승률을 계산
        B가 고른 주사위를 던진 합 bSum은 오름차순 정렬 됨
    */
    public int binarySearch(List<Integer> aSum, List<Integer> bSum) {
        int mid;
        int left;
        int right;
        int len = aSum.size();      // 합계의 갯수
        int total = 0;      // A의 승률

        for(int i=0; i<len; i++) {      // 합계 각각에 대해
            int startIdx = 0;       // A가 B보다 합이 더 큰 갯수
            left = 0;
            right = len - 1;
            while(left <= right) {
                mid = (left + right) / 2;
                if(aSum.get(i) > bSum.get(mid)) {      // A의 승률이 더 크면(무승부는 제외해야 함)
                    left = mid + 1;
                    startIdx = Math.max(startIdx, mid);
                } else {        // B의 승률이 더 크거나 같다면
                    right = mid - 1;
                }
            }
            if(startIdx == 0) {     // A의 현재 합에 대해 승률이 0인 경우
                continue;
            }
            total += startIdx + 1;      // A의 총 승률
        }

        return total;
    }
}