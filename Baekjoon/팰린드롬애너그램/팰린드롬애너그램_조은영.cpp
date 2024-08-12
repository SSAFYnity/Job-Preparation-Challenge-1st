#include <iostream>
#include <math.h>
using namespace std;

int arr[26] = {};

void counting(string s) {
	for (int i = 0; i < s.length(); i++) {
		arr[s[i] - 'a']++;
	}
}

int main() {

	int N = 0;
	bool flag = true;
	string str;

	cin >> N;
	cin >> str;

	counting(str);

	// N이 홀수일 때 (여기서 실수했음. 처음에 arr[middle]-- 해버림
	if (N % 2 ==1) {
		int middle = floor(N / 2);
		arr[str[middle]-'a']--;
	}

	for (int i = 0; i < 26; i++) {
		if (arr[i] % 2 == 1) {
			flag = false;
			break;
		}
	}

	if (flag) cout << "Yes";
	else cout << "No";

	return 0;
}