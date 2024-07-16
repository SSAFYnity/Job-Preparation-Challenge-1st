#include <iostream>
#include <deque>

using namespace std;

void print(deque<int> dq, int idx);

int main(int argc, char** argv) {

	for (int i = 0; i < 10; i++) {
		deque<int> dq;
		int num, tmp;

		cin >> num;
		for (int i = 0; i < 8; i++) {
			cin >> tmp;
			dq.push_back(tmp);
		}

		int sub = 0, target = 1;
		while (target > 0) {
			target = dq.front() - sub - 1;
			if (target < 0) target = 0;
			dq.pop_front();
			dq.push_back(target);
			sub = (sub + 1) % 5;
		}

		print(dq, i);
	}

	return 0;
}

void print(deque<int> dq, int idx) {
	cout << "#" << idx + 1 << " ";
	for (int i = 0; i < 8; i++) {
		cout << dq[i] << " ";
	}
	cout << endl;
}