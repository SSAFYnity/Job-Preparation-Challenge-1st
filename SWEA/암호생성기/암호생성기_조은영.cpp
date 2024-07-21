#include <iostream>
#include <queue>
using namespace std;

int main() {

	int tc = 10;
	int tcNum = 0, input=0;
	
	queue <int>q;
	

	while (tc--) {
		bool flag = false;

		cin >> tcNum;

		for (int i = 0; i < 8; i++) {
			cin >> input;
			q.push(input);
		}

		while (!flag) {
			for (int j = 1; j <= 5; j++) {
				int result = q.front() - j;
				q.pop();
				if (result <= 0) {
					q.push(0);
					flag = true;
					break;
				}
				else {
					q.push(result);
				}
			}
		}

		cout << "#" << tcNum <<" ";
		
		for (int i = 0; i < 8; i++) {
			cout << q.front()<<" ";
			q.pop();
		}

		cout << "\n";
	}

	return 0;
}