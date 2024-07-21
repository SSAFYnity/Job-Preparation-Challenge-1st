#include <string>
#include <vector>
using namespace std;

int n;
vector<vector<int>> com;
vector<int> path;

void permutation(int idx)
{
    if(idx == n) {
        com.push_back(path);
        return;
    }
    path.push_back(10);
    permutation(idx+1);
    path.pop_back();
    path.push_back(20);
    permutation(idx+1);
    path.pop_back();
    path.push_back(30);
    permutation(idx+1);
    path.pop_back();
    path.push_back(40);
    permutation(idx+1);
    path.pop_back();
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
    vector<int> answer;
    n = emoticons.size();
    permutation(0);
    
    int cnt = 0, price = 0;
    for(vector<int>& nowCom: com) {
        int tempCnt = 0, tempTotalPrice = 0;
        for(vector<int>& user: users) {
            int tempPrice = 0;
            for(int i=0; i<emoticons.size(); ++i)
                if(nowCom[i] >= user[0])
                    tempPrice += emoticons[i] * (100 - nowCom[i]) / 100;
            if(tempPrice >= user[1])
                ++tempCnt;
            else
                tempTotalPrice += tempPrice;
        }
        if(tempCnt > cnt || (tempCnt == cnt && tempTotalPrice > price)) {
            cnt = tempCnt;
            price = tempTotalPrice;
        }
    }
    answer.push_back(cnt);
    answer.push_back(price);
    return answer;
}
