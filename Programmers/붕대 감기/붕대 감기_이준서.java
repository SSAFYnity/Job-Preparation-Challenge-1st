class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int T = attacks[attacks.length-1][0];
        int atIdx = 0;
        int continuousSuccess = 1;
        int mxHealth = health;
        for(int t = 1; t<=T; t++){
            if(attacks[atIdx][0] == t){
                health -= attacks[atIdx][1];
                continuousSuccess = 0;
                atIdx++;
                if(health <= 0) return -1;
            } 
            else{
                continuousSuccess++;
                if(continuousSuccess == bandage[0]){
                    health += bandage[2];
                    continuousSuccess = 0;
                }
                health += bandage[1];
                health = Math.min(health,mxHealth);
            }
        }
        
        int answer = health;
        return answer;
    }
}
