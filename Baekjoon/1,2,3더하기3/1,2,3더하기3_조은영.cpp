#include <iostream>
#include <vector>
#define MOD 1000000009
using namespace std;

// 메모이제이션 최적화

int T = 0, N = 0;
vector<long long>dp(1000000 + 1, 1);

void init() {
	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 4;

	for (int i = 3; i <= 1000000; i++) {
		dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3])% MOD;
	}

}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> T;

	init();

	while (T--) {
		cin >> N;
		cout << dp[N] << "\n";
	}

	return 0;
}