class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        int time = 0;
        int atk = 0;
        
        for (int i = 1; i <= attacks[attacks.length-1][0]; i++) {
            if (i == attacks[atk][0]) {
                answer -= attacks[atk][1];
                time = 0;
                atk++;
            } else {
                answer += bandage[1];
                time++;
                
                if (time == bandage[0]) {
                    time = 0;
                    answer += bandage[2];
                }
            }
            
            if (answer <= 0) {
                answer = -1;
                break;
            } else if (answer > health) {
                answer = health;
            }
        }
        
        return answer;
    }
}