#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

bool visited[11];
int n;
double r,max_win;
vector<int> a,b,asum,bsum;
vector<int> res(5);
vector<vector<int>> d;

void calc(int cnt,int sum1,int sum2){
    if(cnt==n/2){
        asum.push_back(sum1);
        bsum.push_back(sum2);

        return;
    }
    for(int i=0;i<6;i++){
        calc(cnt+1,sum1+d[a[cnt]][i],sum2+d[b[cnt]][i]);
    }
}

void pick_dice(int cnt,int idx){ // 주사위 선택
    if(cnt==n/2){
        a.clear(); b.clear();
        asum.clear(); bsum.clear();
        for(int i=0;i<n;i++){
            if(visited[i]) a.push_back(i);
            else b.push_back(i);
        }
    
        calc(0,0,0);
        sort(asum.begin(),asum.end());
        sort(bsum.begin(),bsum.end());
     
        int win=0;
        for(int i=0;i<asum.size();i++){
            win += lower_bound(bsum.begin(),bsum.end(),asum[i])-bsum.begin();
        }
      
        if(win>max_win){
            max_win=win;
            for(int i=0;i<n/2;i++){
                res[i]=a[i];
            }
        }
        return;
    }

    for(int i=idx;i<n;i++){
        if(!visited[i]){
            visited[i]=true;
            pick_dice(cnt+1,i);
            visited[i]=false;
        }
    }
}
vector<int> solution(vector<vector<int>> dice) {
    vector<int> answer;
    n=dice.size();
    d=dice;
    for(int i=0;i<dice.size();i++){
        sort(dice[i].rbegin(),dice[i].rend());
    }
    r=pow(6,dice.size());
    
    pick_dice(0,0);
 
    for(int i=0;i<a.size();i++){
        answer.push_back(res[i]+1);
    }
    return answer;
}
