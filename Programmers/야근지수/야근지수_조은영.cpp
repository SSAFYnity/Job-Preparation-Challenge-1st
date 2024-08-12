#include <string>
#include <vector>
#include <queue>

using namespace std;

long long solution(int n, vector<int> works) {
    priority_queue<int>q;
    long long answer = 0;

    for (int i = 0; i < works.size(); i++) {
        q.push(works[i]);
    }

    for (int i = 0; i < n; i++) {
        int top = q.top();

        if (top == 0) break;

        q.pop();
        top--;
        q.push(top);
    }

    while (!q.empty()) {
        int num = q.top();
        q.pop();
        answer += num * num;
    }

    return answer;
}