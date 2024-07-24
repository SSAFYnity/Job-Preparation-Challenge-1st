class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        
        int curHealth = health;
        int curTime = 0;
        for (int[] attack : attacks) {
            
            int attackTime = attack[0];
            int damage = attack[1];
            
            int between = attackTime - curTime - 1;
            curHealth += between * x;
            if (between / t > 0) {
                curHealth += between / t * y;
            }
            if (curHealth > health) {
                curHealth = health;
            }
            curTime = attackTime;
            curHealth -= damage;
            if (curHealth <= 0) {
                return -1;
            }
        }
        return curHealth;
    }
}