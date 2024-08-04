#include <string>
#include <vector>
#include <algorithm>
#include <iostream>
using namespace std;
#define INF 9212345678912346789;

int n;
long long min_x=INF;
long long min_y=INF;
long long max_x=-INF;
long long max_y=-INF;
vector<pair<long long,long long>> v;

vector<string> solution(vector<vector<int>> line) {
    vector<string> answer;
    n=line.size();
    
    // 교점 구하기
    for(int i=0;i<n-1;i++){
        for(int j=i+1;j<n;j++){
            long long a=line[i][0];
            long long b=line[i][1];
            long long e=line[i][2];
            long long c=line[j][0];
            long long d=line[j][1];
            long long f=line[j][2];
            if(a*d==b*c) continue; // 평행
            if((b*f-e*d)%(a*d-b*c) != 0 || (e*c-a*f)%(a*d-b*c) != 0) continue; // 정수 아님
            
            long long x=(b*f-e*d)/(a*d-b*c);
            long long y=(e*c-a*f)/(a*d-b*c);

            min_x=min(min_x,x);
            min_y=min(min_y,y);
            max_x=max(max_x,x);
            max_y=max(max_y,y);
            v.push_back({y,x});
        }
    }

    string s="";
    for(long long i=min_x;i<=max_x;i++){
        s+=".";
    }
    for(long long i=min_y;i<=max_y;i++){
        answer.push_back(s);
    }
  
    for(auto& k:v){
        answer[k.first-min_y][k.second-min_x]='*';
    }
    
    reverse(answer.begin(),answer.end());
 
    return answer;
}
