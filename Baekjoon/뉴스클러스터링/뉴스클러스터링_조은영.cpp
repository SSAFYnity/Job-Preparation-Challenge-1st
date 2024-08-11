#include <string>
#include <vector>
#include <math.h>
#include <algorithm>
using namespace std;

vector<pair<char, char>>v1;
vector<pair<char, char>>v2;

bool isABC(char ch) {
    if (ch >= 'A' && ch <= 'Z') return true;
    return false;
}

int solution(string str1, string str2) {
    int answer = 0;
    int same = 0, total = 0;

    for (int i = 0; i < str1.length() - 1; i++) {
        char upper1 = toupper(str1[i]);
        char upper2 = toupper(str1[i + 1]);
        if (isABC(upper1) && isABC(upper2)) {
            v1.push_back({ upper1, upper2 });
        }
    }

    for (int i = 0; i < str2.length() - 1; i++) {
        char upper1 = toupper(str2[i]);
        char upper2 = toupper(str2[i + 1]);
        if (isABC(upper1) && isABC(upper2)) {
            v2.push_back({ upper1, upper2 });
        }
    }

    vector<pair<char, char>>v2_copy = v2;

    for (const auto& v : v1) {
        auto it = find(v2_copy.begin(), v2_copy.end(), v);
        if (it != v2_copy.end()) {
            same++;
            v2_copy.erase(it);
        }
    }

    total = v1.size() + v2.size();

    if (total == 0) {
        answer = 65536;
    }
    else {
        // answer=trunc((same/(total-same))*65536); ¿Ã∞« ∆≤∑»¿Ω
        answer = (int)trunc((double)same / (total - same) * 65536);
    }


    return answer;
}