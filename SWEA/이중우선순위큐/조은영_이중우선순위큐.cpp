#include <string>
#include <vector>
#include <string>
#include <set>
#include <sstream>

using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> answer;
    multiset<int>ms;

    char ch = ' ';
    int num = 0;


    for (int i = 0; i < operations.size(); i++) {
        stringstream ss(operations[i]);
        ss >> ch;
        ss >> num;

        if (ch == 'I') {
            ms.insert(num);
        }
        else if (ch == 'D' && !ms.empty()) {
            if (num == 1) {
                ms.erase(--ms.end());
            }
            else if (num == -1) {
                ms.erase(ms.begin());
            }
        }

    }

    if (ms.empty()) {
        answer.push_back(0);
        answer.push_back(0);
    }
    else {
        answer.push_back(*ms.rbegin());
        answer.push_back(*ms.begin());
    }

    return answer;
}