#include <iostream>
#include <string.h>
using namespace std;

int dp[10004];
int T;

int main() {
	cin >> T;
	while (T--) {
		int n;
		cin >> n;
		memset(dp, 0, sizeof(dp));
		for (int i = 1; i <= 3; i++) { // 각 숫자마다 반복
			dp[i]++;
			for (int j = i + 1; j <= n; j++) { // i 보다 큰 dp 테이블 생성하기
				dp[j] += dp[j-i];
			}
		}
		cout << dp[n] << "\n";
	}
}