#include <string>
#include <vector>
using namespace std;

char arr[6][3] {
    {'+','-','*'},
    {'+','*','-'},
    {'-','+','*'},
    {'-','*','+'},
    {'*','-','+'},
    {'*','+','-'}
};

template <typename T>
void func(T& now, int i)
{
    for(;i<now.size()-1;++i)
        now[i] = now[i+1];
}

long long solution(string expression) {
    long long answer = 0;
    vector<long long> nums;
    vector<char> idxs;
    string str;
    for(int i=0;i<expression.size();++i) {
        if(expression[i] == '-' || expression[i] == '+' || expression[i] == '*') {
            nums.push_back(stol(str));
            idxs.push_back(expression[i]);
            str="";
        } else str+=expression[i];
    }
    nums.push_back(stol(str));
    idxs.push_back('.');
    
    for(int i=0;i<6;++i) {
        vector<long long> num = nums;
        vector<char> idx = idxs;
        for(int j=0;j<3;++j) {
            for(int k=0;k<idx.size();++k) {
                if(idx[k] == '.') break;
                if(idx[k] == arr[i][j]) {
                    if(idx[k] == '-')
                        num[k] = num[k] - num[k+1];
                    else if(idx[k] == '+')
                        num[k] = num[k] + num[k+1];
                    else
                        num[k] = num[k] * num[k+1];
                    func(num, k+1);
                    func(idx, k);
                    --k;
                }
            }
        }
        answer = max(answer, abs(num[0]));
    }
    
    return answer;
}
