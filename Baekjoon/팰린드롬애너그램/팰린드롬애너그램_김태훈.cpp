#include <iostream>
#include <map>

using namespace std;

int main(){
    map<char, int> Counter;
    int N;
    
    cin >> N;
    
    for (int i = 0;i < N;i++) {
        char c;
        cin >> c;
        
        if ((N & 1) && i == N/2) continue;
        
        Counter[c]++;
    }
    
    bool isPossible = true;
    
    for (auto &[item, cnt] : Counter) {
        if (cnt & 1) {
            isPossible = false;
            break;
        }
    }
    
    printf("%s", isPossible?"Yes":"No");
    
    return 0;
}