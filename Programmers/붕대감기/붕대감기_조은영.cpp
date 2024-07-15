#include <string>
#include <vector>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int time = 0;  // 현재 시간
    int answer = health;  // 결과값
    int continueCnt = 0;  // 연속 기술 횟수
    int attackCnt = 0;  // 공격 횟수

    int skillTime = bandage[0];
    int healthUp = bandage[1];
    int bonus = bandage[2];

    while (1) {

        time++;

        // 공격
            if (attackCnt < attacks.size() && attacks[attackCnt][0] == time) {
                answer -= attacks[attackCnt][1];
                continueCnt = 0;
                attackCnt++;
            }
        // 공격X
            else {
                answer += healthUp;

                continueCnt++;

                if (continueCnt == skillTime) {
                    continueCnt = 0;
                    answer += bonus;
                }

            }
        if (answer > health) answer = health;
        if (answer <= 0) return -1;
        if (attackCnt == attacks.size()) break;
    }



    return answer;
}