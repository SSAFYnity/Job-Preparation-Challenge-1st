#include <iostream>
#include <string>
#include <math.h>
using namespace std;


int calc(string s, int left, int right) {
    while (left >= 0 && right < s.length()) {
        if (s[left] != s[right]) break;
        left--;
        right++;
    }
    return right - left - 1;
}

int solution(string s)
{
    int answer = 0;
    int len = s.length();

    for (int i = 0; i < len; i++) {
        //Â¦¼ö
        int even = calc(s, i, i + 1);
        //È¦¼ö
        int odd = calc(s, i, i);
        answer = max(answer, max(odd, even));
    }

    return answer;
}