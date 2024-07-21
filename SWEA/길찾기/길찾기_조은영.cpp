#include <iostream>
#include <vector>
using namespace std;

vector<int>v[100];

int dfs(int x) {

	if (x == 99) return 1;

	int size = v[x].size();

	for (int i = 0; i < size; i++) {
		int y = v[x][i];

		if (dfs(y) == 1) {
			return 1;  // 목표 정점을 찾은 경우 상위 호출에서도 1을 반환
		}
	}

	return 0;
}

int main() {

	// 출발점은 0, 도착점은 99
	// 0에서 99로 가는 길이 존재하면 1, 그렇지 않으면 0 출력
	// DFS로 풀어보자. 근데 방향이 있기 때문에 노드 연결할때 조심해야함

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int tc = 10;
	int tcNum = 0, inputNum = 0;
	int a = 0, b = 0;


	while (tc--) {
		cin >> tcNum >> inputNum;

		for (int i = 0; i < 100; i++) v[i].clear();

		for (int i = 0; i < inputNum; i++) {
			cin >> a >> b;
			v[a].push_back(b);
		}

		cout << "#" << tcNum << " " << dfs(0) << "\n";
	}

	return 0;
}