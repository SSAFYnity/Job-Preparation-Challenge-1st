#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> picks, vector<string> minerals) {
    int answer = 21e8;
    vector<int> pick;
    for(int i=0;i<picks.size();++i)
        while(picks[i]--)
            pick.push_back(i);
    
    sort(pick.begin(), pick.end());
    
    do {
        int hp=0;
        for(int i=0, j=0;i<pick.size() && j<minerals.size();++i)
            for(int cnt=0;cnt<5 && j<minerals.size();++cnt,++j) {
                if(pick[i]==0)
                    ++hp;
                else if(pick[i]==1 && minerals[j]=="diamond")
                    hp+=5;
                else if(pick[i]==1)
                    ++hp;
                else if(minerals[j]=="stone")
                    ++hp;
                else if(minerals[j]=="iron")
                    hp+=5;
                else
                    hp+=25;
            }
        answer = min(hp, answer);
    } while(next_permutation(pick.begin(), pick.end()));
    
    return answer;
}
