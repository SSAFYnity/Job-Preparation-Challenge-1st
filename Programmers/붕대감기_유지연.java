package Programmers;

public class Programmers_붕대감기 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
    
        int time =1;
        int index=0;
        int currentHealth= health;
        int castingTime =1;
        

        while(index <attacks.length)
        {
            int attackTime = attacks[index][0];
            int damage = attacks[index][1];
            
            if(time== attackTime){
                
                currentHealth-=damage;
                
                if(currentHealth<=0)
                    return -1;
                
                index++;
                time++;
                castingTime=1;
  
                continue;
            }
            
            currentHealth+=bandage[1];
            
            
            if(castingTime%bandage[0]==0)
            {
                currentHealth+=bandage[2];
            }
            
            if(currentHealth>health)
                currentHealth=health;

            castingTime++;
            time++;
            
           
        }
        
        return currentHealth;
    }
}
