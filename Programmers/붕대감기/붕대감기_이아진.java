class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int countBeforeAttack=0;
        int accumulatedHealth=health;
        int attackedTime=0;

        for(int[] attackInfo : attacks){
            countBeforeAttack=attackInfo[0]-1-attackedTime;
            accumulatedHealth=Math.min(health,
                                      accumulatedHealth
                                       +bandage[2]
                                       *(countBeforeAttack/bandage[0])
                                      +(countBeforeAttack*bandage[1]));

            accumulatedHealth-=attackInfo[1];

            if(accumulatedHealth<=0){
                return -1;
            }else{
                attackedTime=attackInfo[0];
            }

        }

        return accumulatedHealth;
    }
}