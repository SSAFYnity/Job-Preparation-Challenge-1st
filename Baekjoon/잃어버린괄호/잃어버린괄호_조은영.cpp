#include<iostream>
#include <vector>
#include <string>
using namespace std;

int main() {
	string s;
	string number;
	bool minus = false;
	int result = 0;

	cin >> s;

	for (int i = 0; i <= s.size(); i++) {
		if (s[i] == '-' || s[i] == '+' || i == s.size()) {
			if (minus == true) {
				result -= stoi(number);

			}
			else {
				result += stoi(number);

			}
			number = " ";
		}
		else {
			number += s[i];
		}
		if (s[i] == '-') {
			minus = true;
		}

	}

	cout << result << endl;

	return 0;
}