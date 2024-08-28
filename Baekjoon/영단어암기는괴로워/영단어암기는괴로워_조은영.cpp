#include <iostream>
#include <unordered_map>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;

unordered_map<string, int>mp;
vector<string>v;

bool cmp(string a, string b) {
	if (mp[a] == mp[b] && a.length() == b.length()) {
		return a < b;
	}
	else if (mp[a] == mp[b]) {
		return a.length() > b.length();
	}
	else {
		return mp[a] > mp[b];
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(NULL);

	int n = 0, m = 0;
	string st = "";


	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		cin >> st;
		if (st.length() >= m && mp.find(st) == mp.end()) {
			mp.insert({ st,1 });
			v.push_back(st);
		}
		else if (mp.find(st) != mp.end()) {
			mp[st]++;
		}
	} // 빈도 측정, m 이상인 단어만 넣음. 벡터에 단어만 넣음.


	sort(v.begin(), v.end(), cmp);

	for (auto a : v) {
		cout << a << "\n";
	}

	return 0;
}