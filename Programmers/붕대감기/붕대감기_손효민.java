class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health; // 현재 체력
        
        int endIdx = attacks.length;
        int endTime = attacks[endIdx-1][0];
        int win = 0; //연속 성공
        int atIdx=0;
        for(int i=0;i<=endTime;i++){
            if(attacks[atIdx][0] != i){
                win++;
                answer += bandage[1];
                if(win == bandage[0]) {
                    answer += bandage[2];
                    win=0;
                }
                if(answer > health) answer = health;
            }else{
                win=0;
                answer -= attacks[atIdx][1];
                atIdx++;
            }
            if(answer <=0 ) return -1;
        }
        // 모든 공격이 끝난 직후 남은 체력 return
        // System.out.println(answer);
        if(answer <=0 ) return -1;
        return answer;
    }
}
