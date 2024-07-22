function solution(bandage, health, attacks) {
    var answer = 0;
    var currHealth = health;
   
    var prev = 0;
    var gap = 0;

    //기술이 끝나면 그 즉시 붕대감기를 다시 사용한다.
    for(var i = 0; i < attacks.length; i++){
        if(i > 0){
            // -1 : 공격을 당하는 순간에는 체력을 회복할 수 없다.
            gap = (attacks[i][0] - prev)-1;      
            // 기본 회복량
            currHealth += gap * bandage[1];
            // 보너스 체력 지급
            currHealth +=  ( bandage[2] * Math.floor( gap / bandage[0]));
            currHealth = Math.min(currHealth, health);
        }
        
        currHealth -= attacks[i][1];
        if(currHealth <= 0) return -1;
        
        prev = attacks[i][0];
    }

    return currHealth;
}
