#include <iostream>
#include<vector>
#include <string>
#include <algorithm>
#include <map>
using namespace std;

struct info {
	string word;
	int time = 0;
};
int n, m;
vector<info> dic;
map<string, int> data_map;
bool cmp(info first, info second) {
	if (first.time > second.time) {
		return true;
	}
	else if (first.time == second.time) {
		if (first.word.size() > second.word.size()) {
			return true;
		}
		else if (first.word.size() == second.word.size()) {
			if (first.word < second.word) {
				return true;
			}
		}
	}
	return false;

}

void init() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		string s;
		cin >> s;
		if (s.size() >= m) {
			if (data_map.find(s) != data_map.end()) { // 값이 있다면
				dic[data_map[s]].time++;
			}
			else { // 처음 나온 단어라면??
				dic.push_back({ s,1 });
				data_map[s] = dic.size() - 1;
			}

		}
	}
}
void solve() {
	// vector 내부 정렬
	sort(dic.begin(), dic.end(), cmp);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	init();
	solve();
	for (info now : dic) {
		cout << now.word << '\n';
	}

}