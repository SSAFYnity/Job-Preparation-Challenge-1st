#include<iostream>
#define MAX 1000001
#define MOD 1000000009
using namespace std;

int T;
long long dp[MAX];
void init() {
	cin >> T;
}
int dp_F(int r) {
	if (r == 0) return 0;
	if (dp[r] != 0) {
		return dp[r];
	}
	dp[r] = ((dp_F(r - 1)  + dp_F(r - 2)) % MOD + dp_F(r - 3)) % MOD;

	return dp[r];
}
int main() {
	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 4;
	init();
	while (T--) {
		int n;
		cin >> n;
		if (dp[n] == 0) {
			dp[n] = dp_F(n);
		}
		cout << dp[n] << endl;

	}
}