#include <iostream>
using namespace std;

int cnt[100001] = {};
int arr[200001] = {};

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int N = 0, K = 0;


	cin >> N >> K;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int start = 0, end = 0;
	int len = 0; // 현재 길이
	int ans = 0;

	while (start <= end && end < N) {
		
		if (cnt[arr[end]] < K) {
			cnt[arr[end]]++;
			end++;
			ans = max(ans, end-start); // 최대값이 갱신되는 순간
		}
		else {
			cnt[arr[start]]--;
			start++;
		}	
	}

	cout << ans << "\n";

	return 0;
}