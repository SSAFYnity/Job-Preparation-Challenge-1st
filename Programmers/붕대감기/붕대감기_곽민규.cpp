#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int answer = health, time = 0;
    for(auto attack:attacks){
        int t = attack[0], dmg = attack[1], itv = t-time-1;
        time = t;
        answer = min(health,answer + itv/bandage[0]*(bandage[2]+bandage[1]*bandage[0]) + itv%bandage[0]*bandage[1]);
        time = t;
        answer -= dmg;
        if(answer<=0) return -1;
    }
    return answer;
}
