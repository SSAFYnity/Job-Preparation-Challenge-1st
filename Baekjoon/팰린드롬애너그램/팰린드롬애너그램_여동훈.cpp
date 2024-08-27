#include <iostream>
#include <string>
using namespace std;

int N;
string s;
void init() {
	cin >> N;
	cin >> s;
}
bool solve() {
	int ssize = s.size();
	int halfsize = ssize / 2;
	int mem[30] = {0,};
	for (int i = 0; i < halfsize; i++) { // 갯수 저장
		mem[s[i] - 'a']++;
		mem[s[ssize-1 - i] - 'a']++;
	}
	for (int i = 0; i < 30; i++) { // 짝수개가 아니면 NO
		if (mem[i] % 2 != 0) {
			return false;
		}
	}
	return true;
}
int main() {

	init();
	bool result = solve();
	if (result == true) {
		cout << "Yes";
	}
	else {
		cout << "No";
	}


}