#include <iostream>
#include <vector>

using namespace std;
string str;
vector<string> v;
vector<char> op;
int ans;

void split() {
    string tmp = "";
    int idx = 0;
    while (idx < str.size()) {
        if (str[idx] == '+' || str[idx] == '-') {
            v.push_back(tmp);
            op.push_back(str[idx]);
            tmp = "";
        } else {
            tmp += str[idx];
        }
        idx++;
    }
    v.push_back(tmp);
}

void input() {
    cin >> str;
    split();
}

void solve() {
    int cnt = 0;
    for (int i = 0; i < op.size(); i++) {
        if (op[i] == '+') {
            int ret = stoi(v[i - cnt]) + stoi(v[i - cnt + 1]);
            v.erase(v.begin() + i - cnt);
            v.erase(v.begin() + i - cnt);
            v.insert(v.begin() + i - cnt, to_string(ret));
            cnt++;
        }
    }

    ans = stoi(v[0]);
    if (v.size() > 1) {
        for (int i = 1; i < v.size(); i++) {
            ans -= stoi(v[i]);
        }
    }
}

void output() {
    cout << ans;
}

int main() {
    input();
    solve();
    output();
}