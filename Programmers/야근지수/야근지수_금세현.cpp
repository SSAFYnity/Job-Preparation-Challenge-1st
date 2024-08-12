#include <string>
#include <vector>
#include <queue>
#include <cmath>
#include <iostream>
using namespace std;

long long solution(int n, vector<int> works) {
    long long answer = 0;
    
    priority_queue<int> pq;
    for(int i=0;i<works.size();i++){
        pq.push(works[i]);
    }
    while(n--){
        if(pq.empty()) break;
        int num=pq.top();
        pq.pop();
        num--;
        if(num>0) pq.push(num);
    }
    while(!pq.empty()){
        answer += pow(pq.top(),2);
        pq.pop();
    }
    
    return answer;
}
