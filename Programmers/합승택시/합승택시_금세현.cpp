#include <string>
#include <algorithm>
#include <vector>
using namespace std;

int dist[201][201];

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    int answer = 100000007;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(i==j) dist[i][j]=0;
            else dist[i][j]=100000007;
        }
    }
    
    for(int i=0;i<fares.size();i++){
        dist[fares[i][0]][fares[i][1]]=fares[i][2];
        dist[fares[i][1]][fares[i][0]]=fares[i][2];
    }

    for(int k=1;k<=n;k++){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                dist[i][j]=min(dist[i][k]+dist[k][j],dist[i][j]);
            }
        }
    }
  
    for(int i=1;i<=n;i++){
        answer=min(answer,dist[s][i]+dist[i][a]+dist[i][b]);
    }
    
    return answer;
}
