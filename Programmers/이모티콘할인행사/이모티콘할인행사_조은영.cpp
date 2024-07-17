#include <string>
#include <vector>

using namespace std;

int discount[] = { 10,20,30,40 };
vector<pair<int, int>>discount_price(7, { 0,0 }); // 할인율, 할인 가격
vector<int> answer(2, 0);

void dfs(int depth, vector<vector<int>>users, vector<int>emoticons) {

    int emoticon_cnt = emoticons.size();

    if (depth < emoticon_cnt) {
        // 재귀호출로 할인율 순열 구하기
        for (int i = 0; i < 4; i++) {
            discount_price[depth].first = discount[i];
            discount_price[depth].second = emoticons[depth] - (emoticons[depth] * discount[i] / 100);

            dfs(depth + 1, users, emoticons);
        }
    }
    else {
        // 각 유저의 총합 계산
        int subscribe = 0;
        int total_price = 0;

        for (int i = 0; i < users.size(); i++) {
            int standard = users[i][0];
            int max_price = users[i][1];
            int sum_price = 0;

            // 만약 기준보다 높은 할인율이면 구매
            for (int j = 0; j < emoticon_cnt; j++) {
                if (discount_price[j].first >= standard) {
                    sum_price += discount_price[j].second;
                }
            }
            // 총합이 기준을 초과하면 이모티콘 플러스 구독
            if (sum_price >= max_price) {
                sum_price = 0;
                subscribe++;
            }
            // 그렇지 않으면 전체 합에 더하기
            else {
                total_price += sum_price;
            }
        }

        // 이전보다 가입자가 많으면 정답(1번 조건)
        if (subscribe > answer[0]) {
            answer[0] = subscribe;
            answer[1] = total_price;
        }
        else if (subscribe == answer[0] && total_price > answer[1]) {
            answer[1] = total_price;
        }
    }
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {

    dfs(0, users, emoticons);

    return answer;
}