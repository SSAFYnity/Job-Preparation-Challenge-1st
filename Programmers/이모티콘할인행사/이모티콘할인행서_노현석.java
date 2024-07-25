import java.util.*;

class Solution {
    
    static int discount[] = {10, 20, 30, 40}, maxSub = 0, maxSale = 0, discountInfo[], emoticonsInfo[], usersInfo[][];
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        emoticonsInfo = emoticons;
        usersInfo = users;
        discountInfo = new int[emoticons.length];
        
        getDiscount(0);
        answer[0] = maxSub;
        answer[1] = maxSale;
        return answer;
    }
    
    public void getDiscount(int idx) {
        if(idx == discountInfo.length) {
            buy();
            return;
        }
        
        for(int i=0; i<discount.length; i++) {
            discountInfo[idx] = discount[i];
            getDiscount(idx+1);
        }
    }
    
    public void buy() {
        int sub = 0;
        int sale = 0;
        for(int[] user : usersInfo) {
            int userDiscount = user[0];
            int maxPay = user[1];

            int nowSale = 0;
            for(int i=0; i<discountInfo.length; i++) {
                int discountRate = discountInfo[i];
                if(discountRate >= userDiscount) {
                    nowSale += emoticonsInfo[i] * (100 - discountInfo[i]) / 100;
                }
            }
            if(nowSale >= maxPay) sub++;
            else sale += nowSale;
        }
        
        if(maxSub < sub) {
            maxSub = sub;
            maxSale = sale;
        } else if(maxSub == sub) {
            maxSale = Math.max(maxSale, sale);
        }
    }
}