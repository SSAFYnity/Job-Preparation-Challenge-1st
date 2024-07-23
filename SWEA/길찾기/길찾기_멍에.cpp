#include <iostream>
#include <vector>
#include <deque>
#include <array>

using namespace std;

void print(int idx, int ans);
int bfs(int start, array<vector<int>, 100> data, array<bool, 100> isvisited);

int main(int argc, char** argv) {
	int tc, roadCnt;
	array<vector<int>, 100> data;
	array<bool, 100> isvisited;

	for (int i = 0; i < 10; i++) {
		// get data
		cin >> tc >> roadCnt;

		data = {};
		isvisited.fill(false);
		for (int j = 0; j < roadCnt; j++) {
			int start, end;
			cin >> start >> end;
			data[start].push_back(end);
		}

		// find path
		// print result
		print(i, bfs(0, data, isvisited));
	}

	return 0;
}

int bfs(int start, array<vector<int>, 100> data, array<bool, 100> isvisited) {
	deque<int> dq;
	dq.push_front(start);

	while (!dq.empty()) {
		int cur = dq.back();
		dq.pop_back();

		for (int i = 0; i < data[cur].size(); i++) {
			int next = data[cur][i];
			if (!isvisited[next]) {
				dq.push_front(next);
				isvisited[next] = true;
			}
		}

		if (isvisited[99]) return 1;
	}

	return 0;
}

void print(int idx, int ans) {
	cout << "#" << idx + 1 << " " << ans << endl;
	return;
}