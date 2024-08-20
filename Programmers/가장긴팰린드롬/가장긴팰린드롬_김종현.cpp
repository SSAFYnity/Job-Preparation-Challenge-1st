#include <string>
#include <algorithm>
using namespace std;

int solve(string s, int l, int r) {
    while(l >= 0 && r< s.size()) {
        if (s[l] != s[r]) break;
        l--;
        r++;
    }
    return r - l - 1;
}

int solution(string s) {
    int answer=0;
    for (int i = 0; i < s.size(); i++) {
        if(i+answer/2 >= s.size() || i-answer/2 < 0) continue;
        answer = max(answer, max(solve(s, i, i), solve(s, i + 1, i)));
    }
    return answer;
}
