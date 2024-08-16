#include<iostream>
#include<queue>
using namespace std;

int mem[100001];
int num;
int max_cnt;
int result;
queue<int> q;
int arr[200000];

void init() {
	cin >> num;
	cin >> max_cnt;
	for (int i = 0; i < num; i++) {
		cin >> arr[i];
	}
}
void solve() {
	for (int i = 0; i < num; i++) {
		mem[arr[i]]++;
		if (mem[arr[i]] > max_cnt) { // 해당 카운트를 넘으면
			while (mem[arr[i]] > max_cnt) { 
				int front = q.front(); q.pop();
				mem[front]--;
			}
		}
		q.push(arr[i]); // push
		result = result < q.size() ? q.size() : result;

	}


}
void result_print() {
	cout << result;
}
int main() {
	init();
	solve();
	result_print();
}