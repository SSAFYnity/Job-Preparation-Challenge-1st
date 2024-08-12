#include <iostream>
#include <string.h>
using namespace std;
int T,dp[10004];

void input() {
    cin >> T;
}

void solve() {
    int N;
    while (T--) {
        cin >> N;
        memset(dp,0, sizeof(dp));
        for (int i = 1; i <= 3; i++) {
            dp[i]++;
            for (int j = i + 1; j <= N; j++) {
                dp[j] += dp[j - i];
            }
        }
        cout << dp[N] << "\n";
    }

}

int main() {
    input();
    solve();
}