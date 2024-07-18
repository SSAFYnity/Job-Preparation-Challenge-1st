#include <string>
#include <vector>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int answer = 0;
    int cnt = 1;
    int hp = health;
    for(int i=0, timer=0;i<attacks.size();++timer) {
        if(attacks[i][0] == timer) {
            hp -= attacks[i][1];
            if(hp <= 0) {
                answer = -1;
                break;
            }
            ++i;
            cnt = 1;
            continue;
        }
        if(cnt == bandage[0]) {
            hp = hp + bandage[1] + bandage[2] > health ? health : hp + bandage[1] + bandage[2];
            cnt = 1;
        } else {
            hp = hp + bandage[1] > health ? health : hp + bandage[1];
            ++cnt;
        }
    }
    if(answer != -1)
        answer = hp;
    return answer;
}
