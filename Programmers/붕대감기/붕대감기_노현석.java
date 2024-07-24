class Solution {
    static int turn = 0, combo = 0;
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        int maxHP = health;
        for(int[] ele : attacks) {
            int healAmount = heal(bandage, ele[0]);
            health = (health + healAmount > maxHP) ? maxHP : health + healAmount;
            health -= ele[1];
            combo = 0;
            if(health <= 0) return -1;
            answer = health;
        }
        return answer;
    }
    
    public int heal(int[] bandage, int atkTurn) {
        int healTurn = atkTurn - turn - 1;
        int healAmount = healTurn * bandage[1];
        combo += healTurn;
        if(combo >= bandage[0]) {
            healAmount += combo / bandage[0] * bandage[2];
            combo %= bandage[0];
        } 
        turn = atkTurn;
        return healAmount;
    }
}