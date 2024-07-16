class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int time = 0;
        int hp = health;

        for(int i = 0; i < attacks.length; i++) {
            // 회복 진행
            int gap = attacks[i][0] - time - 1;
            hp += gap * bandage[1] + (gap / bandage[0]) * bandage[2];
            hp = Math.min(hp, health);

            // 공격
            hp -= attacks[i][1];
            if(hp <= 0) return -1;

            time = attacks[i][0];
        }

        return hp;
    }
}