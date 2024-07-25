import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static class goal implements Comparable<goal> {
        int plusService;
        int saleMoney;

        public goal(int plusService, int saleMoney) {
            this.plusService = plusService;
            this.saleMoney = saleMoney;
        }

        @Override
        public int compareTo(goal o) {
            return this.plusService != o.plusService ? Integer.compare(o.plusService, this.plusService)
                    : Integer.compare(o.saleMoney, this.saleMoney);
        }
    }

    static int[] discount = new int[] { 10, 20, 30, 40 };
    static int[] eachDiscount;
    static ArrayList<goal> arr;

    public int[] solution(int[][] users, int[] emoticons) {

        eachDiscount = new int[emoticons.length];
        arr = new ArrayList<goal>();
        solve(0, users, emoticons);
        Collections.sort(arr);
        // 최우선 목표 이모티콘 플러스 서비스 가입자 최대한 늘리기
        // 그다음 목표 이모티콘 판매액 최대한 늘리기
        int[] answer = { arr.get(0).plusService, arr.get(0).saleMoney };
        // 리턴값은 이모티콘 플러스 가입수, 매출액
        return answer;
    }

    private void solve(int i, int[][] users, int[] emoticons) {
        if (i == emoticons.length) {
            int cnt = 0;
            int money = 0;
            for (int j = 0; j < users.length; j++) {
                int hap = 0;
                for (int k = 0; k < eachDiscount.length; k++) {
                    if (users[j][0] <= eachDiscount[k]) {
                        hap += emoticons[k] - (emoticons[k] / 100 * eachDiscount[k]);
                    }
                }
                if (hap >= users[j][1]) {
                    cnt++;
                } else {
                    money += hap;
                }
                arr.add(new goal(cnt, money));
            }
            return;

        }
        for (int j = 0; j < discount.length; j++) {
            eachDiscount[i] = discount[j];
            solve(i + 1, users, emoticons);
        }
    }
}