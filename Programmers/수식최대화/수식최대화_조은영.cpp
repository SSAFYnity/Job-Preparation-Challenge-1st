#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstdlib>

using namespace std;

long long applyOperation(long long a, long long b, char op) {
    if (op == '+') return a + b;
    if (op == '-') return a - b;
    if (op == '*') return a * b;
    return 0;
}

long long evaluate(vector<long long> numbers, vector<char> ops, vector<char> precedence) {
    vector<long long> tmpNums = numbers;
    vector<char> tmpOps = ops;


    for (char op : precedence) {
        for (int i = 0; i < tmpOps.size(); ) {
            if (tmpOps[i] == op) {
                long long result = applyOperation(tmpNums[i], tmpNums[i + 1], op);
                tmpNums[i] = result;
                tmpNums.erase(tmpNums.begin() + i + 1);
                tmpOps.erase(tmpOps.begin() + i);
            }
            else {
                i++;
            }
        }
    }
    return tmpNums[0];
}

long long solution(string expression) {
    vector<long long> numbers;
    vector<char> operators;
    vector<char> uniqueOps;

    string num = "";
    for (char c : expression) {
        if (isdigit(c)) {
            num += c;
        }
        else {
            numbers.push_back(stoll(num));
            num = "";
            operators.push_back(c);
            if (find(uniqueOps.begin(), uniqueOps.end(), c) == uniqueOps.end()) {
                uniqueOps.push_back(c);
            }
        }
    }
    numbers.push_back(stoll(num));

    sort(uniqueOps.begin(), uniqueOps.end());
    long long maxResult = 0;
    do {
        long long result = evaluate(numbers, operators, uniqueOps);
        maxResult = max(maxResult, abs(result));
    } while (next_permutation(uniqueOps.begin(), uniqueOps.end()));

    return maxResult;
}

