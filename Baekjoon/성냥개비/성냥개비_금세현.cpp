#include <bits/stdc++.h>
using namespace std;

#define INF 0x987654329876543;
int t;
int num[6]={1,7,4,2,0,8};
long long dp[101];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    cin>>t;
    // 1 2 3 4 5 6 7 8 9 0
    // 2 5 5 4 5 6 3 7 6 6

    // 최솟값 구하기
    for (int i=0;i<101;i++) {
        dp[i]=INF;
    }
    dp[2]=1; dp[3]=7; dp[4]=4; dp[5]=2; dp[6]=6; dp[7]=8;
    for (int i=8;i<101;i++) {
        for (int j=2;j<=7;j++) {
            dp[i]=min(dp[i],dp[i-j]*10+num[j-2]);
        }
        // cout<<dp[i]<<'\n';
    }

    while (t--) {
        string max_value="";
        int n; cin>>n;

        // 최댓값 구하기
        if(n%2==0) {
            for (int i=0;i<n/2;i++) {
                max_value += "1";
            }
        }else {
            max_value += "7";
            for (int i=0;i<(n-3)/2;i++) {
                max_value += "1";
            }
        }
        cout<<dp[n]<<' '<<max_value<<'\n';
    }
}
