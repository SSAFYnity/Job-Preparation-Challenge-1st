#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// bandage_len은 배열 bandage의 길이입니다.
// attacks_rows는 2차원 배열 attacks의 행 길이, attacks_cols는 2차원 배열 attacks의 열 길이입니다.
int solution(int bandage[], size_t bandage_len, int health, int** attacks, size_t attacks_rows, size_t attacks_cols) {
    int answer = health;
    int time = 1;
    int heal_time=0;
    for(int i=0;i<attacks_rows;i++){
        for(int j=time;j<attacks[i][0];j++){
            answer+=bandage[1];
            time+=1;
            heal_time+=1;
            if(heal_time==bandage[0]){
                answer+=bandage[2];
                heal_time=0;
            }
            if(answer>health){
                answer=health;
            }
            //printf("%d\n",answer);
        }
        time+=1;
        heal_time=0;
        answer-=attacks[i][1];
        if(answer<=0){
            answer=-1;
            break;
        }
        //printf("%d\n",answer);
    }
    
    return answer;
}