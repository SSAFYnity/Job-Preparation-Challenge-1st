#include <string>
#include <vector>
#include <queue>
#include <unordered_set>
using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> answer;
    int num;
    queue<int> temp;
    priority_queue<int, vector<int>, less<>> maxQ;
    priority_queue<int, vector<int>, greater<>> minQ;
    unordered_set<int> s;
    
    for(string& str: operations) {
        if(str[0]=='I') {
            num = stoi(&str[2]);
            maxQ.push(num);
            minQ.push(num);
            temp.push(num);
        } else {
            num = stoi(&str[2]);
            if(num > 0 && !maxQ.empty())
                maxQ.pop();
            else if(num < 0 && !minQ.empty())
                minQ.pop();
            
            if(!temp.empty()) temp.pop();
        }
    }
    
    if(!maxQ.empty() && !minQ.empty() && !temp.empty()) {
        answer.push_back(maxQ.top());
        for(;!maxQ.empty();maxQ.pop())
            s.insert(maxQ.top());
        
        for(;!minQ.empty();minQ.pop())
            if(s.find(minQ.top())!=s.end())
                break;
        answer.push_back(minQ.top());
    } else {
        answer.push_back(0);
        answer.push_back(0);
    }
    
    return answer;
}
