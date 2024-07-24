import java.util.*;

class Solution {
    
    private int[] discounts = { 10, 20, 30, 40 };
    private int[][] users;
    private int[] emoticons;
    private int[] discounted;
    private int[] answer = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        this.users = users;
        this.emoticons = emoticons;
        this.discounted = new int[emoticons.length];
        
        perm(0);
        
        return answer;
    }
    
    public void perm(int cnt) {
        if (cnt == emoticons.length) {
            int totalAmount = 0;
            int emoticonPlus = 0;
            for (int[] user : users) {
                int amount = 0;
                for (int i = 0; i < discounted.length; i++) {
                    // 할인 조건 만족
                    if (discounted[i] >= user[0]) {
                        amount += emoticons[i] / 100 * (100 - discounted[i]);
                    }
                }
                // 이모티콘 플러스 구매 조건 만족
                if (amount >= user[1]) {
                    emoticonPlus++;
                } else {
                    totalAmount += amount;
                }
            }
            if (answer[0] < emoticonPlus) {
                answer[0] = emoticonPlus;
                answer[1] = totalAmount;
                return;
            }
            if (answer[0] == emoticonPlus) {
                answer[1] = Math.max(answer[1], totalAmount);
                return;
            }
            return;
        }
        
        for (int i = 0; i < discounts.length; i++) {
            discounted[cnt] = discounts[i];
            perm(cnt + 1);
        }
    }
}