import java.util.List;
import java.util.ArrayList;

class Solution {
    static int m;      // 이모티콘 갯수
    static List<Integer[]> discountLst = new ArrayList();       // 인덱스 순열을 담는 리스트(percent, percentArr의 인덱스로 사용)
    static float[] percent = {0.0f, 0.9f, 0.8f, 0.7f, 0.6f};    // 할인을 적용한 후 비율
    static int[] percentArr = {0, 10, 20, 30, 40};              // 사용자의 구매 기준 퍼센트와 비교하기 위한 배열
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];      // [이모티콘 플러스 서비스 가입 수, 이모티콘 팬매액]
        m = emoticons.length;
        
        dfs(0, 0, new Integer[m]);      // 이모티콘 별 할인율을 순열로 생성하기 위한 재귀 호출
        
        for(Integer[] discountArr : discountLst) {      // 만든 할인율 순열을 반복
            int[] result = purchase(users, emoticons, discountArr);     // 이모티콘 구매 비용 및 가입자 수 계산을 위한 호출
            
            if(answer[0] < result[0]) {     // 이모티콘 플러스 서비스 가입 수가 더 늘었으면
                answer[0] = result[0];      // 이모티콘 플러스 서비스 가입 수 갱신
                answer[1] = result[1];      // 이모티콘 판매액 갱신
            } else if(answer[0] == result[0] && answer[1] < result[1]) {    // 2순위가 이모티콘 판매액
                answer[1] = result[1];      // 이모티콘 판매액 갱신
            }
        }
        return answer;
    }
    
    /*
        순열 생성
        visited: 선택한 값은 비트로 표현
        cnt: 선택한 이모티콘 갯수
        permutationArr: 순서대로 선택한 값을 담은 배열
    */
    public void dfs(int visited, int cnt, Integer[] permutationArr) {
        if(cnt == m)    {       // 모든 이모티콘에 할인율 적용
            Integer[] tmp = new Integer[m];
            for(int i=0; i<m; i++) {
                tmp[i] = permutationArr[i];
            }
            discountLst.add(tmp);
            return;
        } else {
            for(int i=1; i<=4; i++) {
                if((visited & (1<<i)) == 1) {      // 선택했던 값
                    continue;
                }
                permutationArr[cnt] = i;
                dfs(visited | (1<<i), cnt + 1, permutationArr);
            }
        }
    }
    
    /*
        이모티콘 플러스 서비스 가입자 수, 이모티콘 판매액을 구하기 위해 유저별로 계산
        users: 사용자 배열로서 [[비율, 가격], ...]으로 이루어짐
        emoticons: 이모티콘 가격 배열
        discountArr: 할인 비율 순열 인덱스
    */
    public int[] purchase(int[][] users, int[] emoticons, Integer[] discountArr) {
        int peopleCnt = 0;      // 이모티콘 플러스 서비스 가입자 수
        int earnMoney = 0;          // 이모티콘 판매액
        for(int[] user : users) {       // 한 유저
            int userMoney = 0;          // 한 유저의 구매 금액 총합
            for(int idx=0; idx<discountArr.length; idx++) {    // 모든 이모티콘 탐색
                if(user[0] <= percentArr[discountArr[idx]]) {   // 유저가 구매하려는 비율 그 이상이면
                    userMoney += emoticons[idx] * percent[discountArr[idx]];       // 이모티콘 구매
                }
            }
            // 사용자가 원하는 비율 이상의 이모티콘을 모두 구매 후 이모티콘 플러스 서비스 가입 여부 결정
            if(user[1] <= userMoney) {      // 이모티콘 플러스에 가입하는 게 더 이득
                peopleCnt++;        // 이모티콘 플러스 가입자 수 증가
            } else {
                earnMoney += userMoney;     // 이모티콘 판매액 증가
            }
        }       
        return new int[]{peopleCnt, earnMoney};     // 이모티콘 플러스 서비스 가입자 수, 이모티콘 판매액 반환
    }
}