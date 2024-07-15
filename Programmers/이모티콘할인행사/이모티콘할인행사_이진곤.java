class Solution {
    final int[] SALE_PERCENT = new int[] { 10, 20, 30, 40 };
    int[][] users;
    int[] emoticons;
    int[] sales;
    int maxJoinCnt;
    int maxPrice;

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        this.sales = new int[emoticons.length];

        getSalePercent(0);
        int[] answer = { maxJoinCnt, maxPrice };
        return answer;
    }

    void process() {
        int newJoinCnt = 0;
        int price = 0;

        for (int[] user : users) {
            int buyPrice = 0;
            boolean isJoinable = false;
            for (int i = 0; i < emoticons.length; i++) {
                int salePrice = emoticons[i] * (100 - sales[i]) / 100;
                if (sales[i] >= user[0]) {
                    buyPrice += salePrice;
                    if (buyPrice >= user[1]) {
                        isJoinable = true;
                        break;
                    }
                }
            }
            if (isJoinable) {
                newJoinCnt++;
            }
            else {
                price += buyPrice;
            }
        }

        if (newJoinCnt > maxJoinCnt) {
            maxJoinCnt = newJoinCnt;
            maxPrice = price;
        }
        else if (newJoinCnt == maxJoinCnt) {
            maxPrice = Math.max(maxPrice, price);
        }
    }

    void getSalePercent(int idx) {
        if (idx >= emoticons.length) {
            process();
            return;
        }

        for (int i = 0; i < SALE_PERCENT.length; i++) {
            sales[idx] = SALE_PERCENT[i];
            getSalePercent(idx + 1);
        }
    }
}