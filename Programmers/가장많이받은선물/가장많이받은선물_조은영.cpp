#include <string>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

map<string, int>mp;
map<string, int>give;
map<string, int>receive;
int relation[51][51];

int solution(vector<string> friends, vector<string> gifts) {
    int answer = 0;

    for (int i = 0; i < friends.size(); i++) {
        mp[friends[i]] = i;
    }

    for (int i = 0; i < gifts.size(); i++) {
        string name = "";
        string temp = "";
        for (int j = 0; j < gifts[i].length(); j++) {
            if (gifts[i][j] == ' ') {
                name = temp;
                temp = "";
                continue;
            }
            temp += gifts[i][j];
        }
        relation[mp[name]][mp[temp]]++;
        give[name]++;
        receive[temp]++;
    }

    int len = friends.size();


    for (int i = 0; i < len; i++) {
        int cnt = 0;
        // i�� ���� ���� ���ϱ�
        for (int j = 0; j < len; j++) {
            // �ְ� ���� ����� ���� (i�� �� ���� ��)
            if (relation[i][j] > relation[j][i]) {
                cnt++;
            }
            else if (relation[i][j] == relation[j][i]) {
                // �ְ� ���� ����� ���ų� Ƚ���� ����
                int first = give[friends[i]] - receive[friends[i]];
                int second = give[friends[j]] - receive[friends[j]];
                if (second < first) cnt++;
            }
        }
        answer = max(answer, cnt);
    }

    return answer;
}