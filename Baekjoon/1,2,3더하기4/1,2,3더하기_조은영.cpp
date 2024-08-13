#include <iostream>
using namespace std;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int T = 0, N = 0;
	int arr[10001] = {};

	fill(arr, arr + 10001, 1);

	cin >> T;

	for (int i = 2; i < 10001; i++) {
		arr[i] += arr[i - 2];
	}

	for (int j = 3; j < 10001; j++) {
		arr[j] += arr[j - 3];
	}

	while (T--) {
		cin >> N;

		cout << arr[N] << "\n";
	}

	return 0;
}