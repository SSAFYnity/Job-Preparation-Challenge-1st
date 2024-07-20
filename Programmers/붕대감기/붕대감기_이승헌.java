package 붕대감기;

import java.util.HashMap;
import java.util.Map;

public class 붕대감기_이승헌 {
    public static void main(String[] args) {
        int solution = solution(new int[]{5, 1, 5}, 30, new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}});
        System.out.println("solution = " + solution);
        int solution1 = solution(new int[]{3, 2, 7}, 20, new int[][]{{1, 15}, {5, 16}, {8, 6}});
        System.out.println("solution1 = " + solution1);
        int solution2 = solution(new int[]{4, 2, 7}, 20, new int[][]{{1, 15}, {5, 16}, {8, 6}});
        System.out.println("solution2 = " + solution2);
        int solution3 = solution(new int[]{1, 1, 1}, 5, new int[][]{{1, 2}, {3, 2}});
        System.out.println("solution3 = " + solution3);
    }

    public static int solution(int[] bandage, int health, int[][] attacks) {
        Map<Integer, Integer> attackInfo = new HashMap<>();
        int curHP = health;
        int endTime = 0;

        for (int[] attack : attacks) {
            attackInfo.put(attack[0], attack[1]);
            endTime = attack[0];
        }

        int time = 0;

        for (int i = 1; i <= endTime; i++) {
            if (attackInfo.containsKey(i)) {
                curHP -= attackInfo.get(i);
                time = 0;
            } else {
                curHP += bandage[1];
                time++;

                if (time == bandage[0]) {
                    curHP += bandage[2];
                    time = 0;
                }
                if (curHP > health) {
                    curHP = health;
                }
            }

            if (curHP > 0) {
                continue;
            }
            return -1;
        }
        return curHP;
    }
}
