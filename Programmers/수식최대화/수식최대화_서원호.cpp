#include <string>
#include <vector>
#include <iostream>
#include <deque>
using namespace std;

char ops[] = {'*', '+', '-'};
bool check[3];
char order[3];
long long result = 0;

long long calc(deque<char> operators, deque<long long> operands) {
    deque<char> oprs;
    deque<long long> opds;
    
    for (char op : order) {
        if (operators.empty()) {
            break;
        }
        oprs.push_back(operators.front()); operators.pop_front();
        opds.push_back(operands.front()); operands.pop_front();
        while (true) {
            long long num2 = operands.front(); operands.pop_front();
            char oper = oprs.back();
            if (oper == op) {
                oprs.pop_back();
                long long num1 = opds.back(); opds.pop_back();
                if (oper == '+') {
                    opds.push_back(num1 + num2);
                } else if (oper == '-') {
                    opds.push_back(num1 - num2);
                } else {
                    opds.push_back(num1 * num2);
                }
                if (operators.empty()) {
                    break;
                }
                oprs.push_back(operators.front()); operators.pop_front();
                continue;
            }
            
            opds.push_back(num2);
            if (operators.empty()) {
                break;
            }
            oprs.push_back(operators.front()); operators.pop_front();
        }
        
        while (!oprs.empty()) {
            operators.push_back(oprs.front()); oprs.pop_front();
        }
        while (!opds.empty()) {
            operands.push_back(opds.front()); opds.pop_front();
        }
    }
    
    return operands.front();
}

void search(deque<char> &operators, deque<long long> &operands, int depth) {
    if (depth == 3) {
        result = max(result, abs(calc(operators, operands)));
        return;
    }
    
    for (int i = 0; i < 3; i++) {
        if (check[i]) {
            continue;
        }
        order[depth] = ops[i];
        check[i] = true;
        search(operators, operands, depth + 1);
        check[i] = false;
    }
}

long long solution(string expression) {
    
    deque<char> operators;
    deque<long long> operands;
    
    string s = "";
    for (char c : expression) {
        if (c == '*' || c == '+' || c == '-') {
            operands.push_back(stoi(s));
            s = "";
            operators.push_back(c);
            continue;
        }
        s += c;
    }
    operands.push_back(stoll(s));
    
    search(operators, operands, 0);
    
    return result;
}