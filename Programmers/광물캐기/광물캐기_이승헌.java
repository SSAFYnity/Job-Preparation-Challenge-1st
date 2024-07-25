package 광물캐기;

import java.util.Arrays;

public class 광물캐기_이승헌 {

    static int[][] fatigues;
    static int len;
    static int result;

    public static void main(String[] args) {
        //12
        int solution = solution(new int[]{1, 3, 2},
                new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"});
        //50
        System.out.println("solution = " + solution);
        int solution1 = solution(new int[]{0, 1, 1},
                new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron",
                        "iron", "diamond"});

        System.out.println("solution1 = " + solution1);
    }

    public static int solution(int[] picks, String[] minerals) {
        result = Integer.MAX_VALUE;
        int count = 0;
        int idx = 0;
        len = minerals.length / 5;
        if (minerals.length % 5 != 0) {
            len++;
        }

        fatigues = new int[len][3];
        int[] tempFatigue = new int[3]; // dia iron stone

        for (String mineral : minerals) {
            count++;

            if (mineral.equals("diamond")) {
                tempFatigue[0]++;
                tempFatigue[1]+= 5;
                tempFatigue[2]+= 25;
            } else if (mineral.equals("iron")) {
                tempFatigue[0]++;
                tempFatigue[1]++;
                tempFatigue[2] += 5;
            } else if (mineral.equals("stone")) {
                tempFatigue[0]++;
                tempFatigue[1]++;
                tempFatigue[2]++;
            }

            if (count != 5) {
                continue;
            }

            System.arraycopy(tempFatigue, 0, fatigues[idx++], 0, 3);
            Arrays.fill(tempFatigue, 0);
            count = 0;
        }
        if (count != 0) {
            System.arraycopy(tempFatigue, 0, fatigues[idx], 0, 3);
        }

        dfs(Arrays.copyOf(picks, 3), 0, 0);
        return result;
    }

    private static void dfs(int[] picks, int idx, int fatigue) {
        if (idx == len) {
            result = Math.min(result, fatigue);
            return;
        } else if (picks[0] == 0 && picks[1] == 0 && picks[2] == 0) {
            result = Math.min(result, fatigue);
            return;
        }

        if(picks[0] > 0){
            picks[0] --;
            dfs(picks, idx + 1, fatigue + fatigues[idx][0]);
            picks[0] ++;
        }
        if(picks[1] > 0){
            picks[1] --;
            dfs(picks, idx + 1, fatigue + fatigues[idx][1]);
            picks[1] ++;
        }
        if(picks[2] > 0){
            picks[2] --;
            dfs(picks, idx + 1, fatigue + fatigues[idx][2]);
            picks[2] ++;
        }
    }
}
