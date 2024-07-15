class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        int now=0;
        for(int[] attack: attacks){
            int time = attack[0]-now-1;
            now=attack[0];
            answer+= time*bandage[1];
            answer+=(time/bandage[0])*bandage[2];
            if(answer>health) answer = health;
            answer-=attack[1];
            if(answer<=0)
                return -1;

        }
        return answer;
    }
}