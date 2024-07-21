class Solution {

    public int solution(int[] bandage, int health, int[][] attacks) {
        int hp = health;

        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];

        int attackSize = attacks.length;

        int time = 0;

        for (int i = 0; i < attackSize; i++) {

            int attackTime = attacks[i][0];
            int attackDamage = attacks[i][1];
            int timeDiff = attackTime - time;

            hp += timeDiff * x;
            hp += (timeDiff / t) * y;
            if (hp > health) hp = health;

            time = attackTime + 1;
            hp -= attackDamage;

            if (hp <= 0) {
                hp = -1;
                break;
            }
        }

        return hp;
    }

}