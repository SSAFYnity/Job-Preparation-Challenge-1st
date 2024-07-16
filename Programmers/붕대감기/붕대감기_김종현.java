class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int hp = health;
        int prevT = 0;
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];
        
        answer = hp;
        for(int i=0; i<attacks.length; i++) {
            int currT = attacks[i][0];
            int damage = attacks[i][1];
            
            // 회복
            hp += ((currT-prevT-1) / t) * (t*x+y);
            hp += ((currT-prevT-1) % t) * x;
            
            // 최대 hp 제한
            if(hp > health) hp = health;
            
            // 데미지
            hp -= damage;
            if(hp <= 0) {
                answer = -1;
                break;
            } else {
                answer = hp;
                prevT = currT;
            }
        }
        return answer;
    }
}