class Solution {
    private int size, member, money;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};

        size = emoticons.length;

        // Brute Force
        // 각 할인율 모두 적용해 계산해보기
        int[][] temp = new int[size][2];
        recursion(temp, 0, users, emoticons);

        return new int[] {member, money};
    }

    private void recursion(int[][] temp, int idx, int[][] users, int[] emoticons) {
        if(idx == size) {
            int sign_cnt = 0;
            int sum = 0;

            for(int i = 0; i < users.length; i++) {
                int ps = 0; // 사용자의 소비
                for(int j = 0; j < size; j++) {
                    if(temp[j][0] >= users[i][0]) { // 사용자가 이모티콘 구매
                        ps += temp[j][1];
                    }
                }
                if (ps >= users[i][1]) { // 사용자의 일정 금액 초과 -> 이모티콘 플러스 구매
                    sign_cnt++;
                } else { // 이모티콘 플러스 구매 안함
                    sum += ps;
                }
            }

            if(member < sign_cnt) {
                member = sign_cnt;
                money = sum;
            } else if (member == sign_cnt && sum > money) {
                money = sum;
            }
            return;
        }

        for(int i = 10; i <= 40; i+=10) {
            temp[idx][0] = i;
            temp[idx][1] = emoticons[idx] - emoticons[idx]/100 * i;
            recursion(temp, idx+1, users, emoticons);
        }
    }
}