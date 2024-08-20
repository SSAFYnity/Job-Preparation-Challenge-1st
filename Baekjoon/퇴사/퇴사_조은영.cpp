#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int N = 0;
	int max_cost = 0;

	int dp[16] = { 0, };
	int T[16] = { 0, };
	int P[16] = { 0, };

	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> T[i] >> P[i];
	}

	for (int i = 0; i <= N; i++) {

		dp[i] = max(dp[i], max_cost);
		
		if (T[i] + i <= N) {
			dp[T[i] + i] = max(dp[T[i] + i], dp[i] + P[i]);
		}
		
		max_cost = max(dp[i], max_cost);

	}

	cout << max_cost << "\n";

	return 0;
}