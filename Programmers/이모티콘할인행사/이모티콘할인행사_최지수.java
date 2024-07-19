import java.util.*;

class Solution {

    static int[] sales, answer;

    public void calculate(int[][] users, int[] emoticons) {
        int signUpPlus = 0;
        int earn = 0;
        for (int[] u: users) {
            int shoppingCart = 0;
            for (int i = 0; i < emoticons.length; i++) {
                int c = emoticons[i];
                if (u[0] <= sales[i]) {
                    shoppingCart += c*(100-sales[i])/100;
                    if (shoppingCart >= u[1]) {
                        signUpPlus++;
                        shoppingCart = 0;
                        break;
                    }
                }
            }
            earn += shoppingCart;
        }

        if (answer[0] < signUpPlus) {
            answer[0] = signUpPlus;
            answer[1] = earn;
        } else if (answer[0] == signUpPlus) {
            answer[1] = Math.max(answer[1], earn);
        }
    }

    public void back(int p, int[][] users, int[] emoticons) {
        if (p >= emoticons.length) {
            calculate(users, emoticons);
            return;
        }

        for (int i = 10; i <= 40; i += 10) {
            sales[p] = i;
            back(p+1, users, emoticons);
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        sales = new int[emoticons.length];

        back(0, users, emoticons);

        return answer;
    }
}