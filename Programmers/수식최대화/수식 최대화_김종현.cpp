#include <string>
#include <vector>
using namespace std;
string comp[6][3] = {{"+", "-", "*"},
                     {"+", "*", "-"},
                     {"*", "-", "+"},
                     {"*", "+", "-"},
                     {"-", "+", "*"},
                     {"-", "*", "+"}};

vector<string> split(string str) {
    vector<string> temp;
    string tmp = "", s = "";
    int idx = -1;
    while (++idx < str.size()) {
        if (str[idx] == '+' || str[idx] == '-' || str[idx] == '*') {
            temp.push_back(tmp);
            tmp = "";
            temp.push_back(s += str[idx]);
            s = "";
        } else tmp += str[idx];
    }
    temp.push_back(tmp);
    return temp;
}

long long calc(string s1, string s2, string op) {
    if (op == "+")
        return stoll(s1) + stoll(s2);
    else if (op == "-")
        return stoll(s1) - stoll(s2);
    else
        return stoll(s1) * stoll(s2);
}


long long solution(string expression) {
    long long answer = 0;
    // 문자열 스플릿 (+, -, *);
    vector<string> s = split(expression);

    for (int i = 0; i < 6; i++) {
        vector<string> ss(s);
        for (int j = 0; j < 3; j++) {
            int len = ss.size();
            for (int k = 1; k < len; k += 2) {
                if (ss[k] == comp[i][j]) {
                    ss[k] = to_string(calc(ss[k - 1], ss[k + 1], ss[k]));
                    ss.erase(ss.begin() + k + 1);
                    ss.erase(ss.begin() + k - 1);
                    k -= 2;
                }
            }
        }
        answer = max(answer, abs(stoll(ss[0])));
    }
    return answer;
}