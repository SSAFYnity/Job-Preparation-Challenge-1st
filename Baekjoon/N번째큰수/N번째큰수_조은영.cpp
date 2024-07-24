#include <iostream>
#include <set>
using namespace std;


int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	multiset<int>ms;

	int N = 0;
	int input = 0;

	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> input;

			if (ms.size() < N) ms.insert(input);
			else {
				int minValue = *ms.begin();
				if (minValue < input) {
					ms.erase(ms.begin());
					ms.insert(input);
				}
			}
		}
	}

	cout << *ms.begin() << "\n";

	return 0;
}