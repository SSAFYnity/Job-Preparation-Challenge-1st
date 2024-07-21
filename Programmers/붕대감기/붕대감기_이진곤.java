class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int atkIdx = 0;
        int healTime = 0;

        for (int i = 1; i <= attacks[attacks.length - 1][0]; i++) {
            if (i == attacks[atkIdx][0]) {
                health -= attacks[atkIdx][1];
                if (health <= 0) {
                    return -1;
                }
                healTime = 0;
                atkIdx++;
            }
            else {
                health += bandage[1];
                healTime++;
                if (healTime == bandage[0]) {
                    healTime = 0;
                    health += bandage[2];
                }
                if (health > maxHealth) {
                    health = maxHealth;
                }
            }
        }
        return health;
    }
}