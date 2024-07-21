class Solution {
    
    static int[] discountRates = {10, 20, 30, 40};
    static int maxPlusSubscribers = 0;
    static int maxSalesAmount = 0;
    static int[][] usersArray;
    static int[] emoticonsArray;
    public int[] solution(int[][] users, int[] emoticons) {
        usersArray = users;
        emoticonsArray = emoticons;
        int[] discounts = new int[emoticons.length];
        dfs(discounts, 0);
        int[] answer = new int[] {maxPlusSubscribers,maxSalesAmount};
        return answer;
    }
    
    public void dfs(int[] discounts, int depth){
        if(depth == emoticonsArray.length){
            calculation(discounts);
            return;
        }
        
        for (int rate : discountRates) {
            discounts[depth] = rate;
            dfs(discounts, depth + 1);
        }
    }
    public void calculation(int[] discounts){
        int plusSubscribers = 0;
        int salesAmount = 0;

        for (int[] user : usersArray) {
            int userDiscountThreshold = user[0];
            int userBudget = user[1];
            int totalSpent = 0;
            
            for(int i = 0; i<emoticonsArray.length; i++){
                if(discounts[i] >= userDiscountThreshold){
                    totalSpent += emoticonsArray[i] * (100 - discounts[i]) / 100;
                }
            }
            
            if(totalSpent >= userBudget) plusSubscribers++;
            else salesAmount += totalSpent;
        }
        
        if(plusSubscribers > maxPlusSubscribers){
            maxPlusSubscribers = plusSubscribers;
            maxSalesAmount = salesAmount;
        }else if(plusSubscribers == maxPlusSubscribers){
            maxSalesAmount = Math.max(maxSalesAmount, salesAmount);
        }
    }
}

