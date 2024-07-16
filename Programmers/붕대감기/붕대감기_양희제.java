// 양희제 - 붕대 감기

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int remainHealth = health;
        int attacksIdx = 0;
        int healTime = 0;
        int time = 0;
        
        while (time <= 1000 && attacksIdx < attacks.length) {
            time += 1;
            if (attacks[attacksIdx][0] == time) {
                remainHealth -= attacks[attacksIdx][1];
                if (remainHealth <= 0) {
                    remainHealth = -1;
                    break;
                }
                
                healTime = 0;
                attacksIdx += 1;
            } else {
                healTime += 1;
                if (healTime == bandage[0]) {
                    remainHealth += bandage[1] + bandage[2];
                    healTime = 0;
                } else {
                    remainHealth += bandage[1];    
                }
                remainHealth = remainHealth > health ? health : remainHealth;
                
            }
        }
        
        return remainHealth;
    }
}