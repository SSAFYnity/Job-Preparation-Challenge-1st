import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int nowHealth = health;     // 현재 체력
        int lastSecond = attacks[attacks.length - 1][0];        // 마지막 초
        int atkIdx = 0;     // 다음 공격 index
        int continueCnt = 0;    // 연속 힐 수
        
        for(int t = 1; t <= lastSecond; t++){
            
            if(t == attacks[atkIdx][0]){
                nowHealth -= attacks[atkIdx][1];
                atkIdx++;
                continueCnt = 0;
                if(nowHealth <= 0)
                    return -1;
            } else {
                continueCnt++;
                nowHealth += bandage[1];
                if(continueCnt == bandage[0]){
                    nowHealth += bandage[2];
                    continueCnt = 0;
                }
                nowHealth = nowHealth > health ? health : nowHealth;
            }
        }
        
        return nowHealth;
    }
}