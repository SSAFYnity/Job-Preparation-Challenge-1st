#include <string>
#include <vector>
#include <queue>
#include <functional>
using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> answer;
    
    priority_queue<int, vector<int>, greater<int>> q1;
    priority_queue<int, vector<int>> q2;
    for (string op : operations) {
        int num = stoi(op.substr(2));
        if (op[0] == 'I') {
            q1.push(num);
            continue;
        }
        
        if (num == 1) {
            while (!q1.empty()) {
                q2.push(q1.top()); q1.pop();
            }
            if (!q2.empty()) {
                q2.pop();
            }
            continue;
        }
        
        while (!q2.empty()) {
            q1.push(q2.top()); q2.pop();
        }
        if (!q1.empty()) {
            q1.pop();
        }
    }
    
    int mn = 0, mx = 0;
    if (!q1.empty()) {
        mn = q1.top();
        mx = q1.top();
        q1.pop();
        while (!q1.empty()) {
            mx = q1.top(); q1.pop();
        }
    }
    
    if (!q2.empty()) {
        mn = q2.top();
        mx = q2.top();
        q2.pop();
        while (!q2.empty()) {
            mn = q2.top(); q2.pop();
        }
    }
    return vector<int> {mx, mn};
}