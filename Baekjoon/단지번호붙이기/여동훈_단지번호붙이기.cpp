#include <iostream>
#include <utility>
#include <queue>
#include <algorithm>
using namespace std;
typedef pair<int, int> loc;

bool cmp(const int &first, const int &second) {
	return first < second;
}

int N;
int num; // 단지 수
char MAP[25][26];
int visited[25][25];
vector <int> result;

void init() {
	cin >> N;
	num = 0;
	for (int i = 0; i < N; i++) {
		cin >> MAP[i];
	}
}
void bfs(loc param) {
	const int dy[] = { -1,0,1,0 };
	const int dx[] = { 0,-1, 0 ,1 };
	num++;

	queue<loc> q;
	q.push(param);
	visited[param.first][param.second] = 1; // 방문 처리
	int cnt = 1; // 단지 내 집 수

	while (!q.empty()) {
		loc now = q.front(); q.pop();
		for (int i = 0; i < 4; i++) {
			int ny = now.first + dy[i];
			int nx = now.second + dx[i];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
			if (visited[ny][nx] == 1) continue;
			if (MAP[ny][nx] == '0') continue;
			visited[ny][nx] = 1;
			q.push({ ny,nx });
			cnt++;
		}
	}
	result.push_back(cnt);

}

int main() {
	init();
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (MAP[i][j] == '0') continue;
			if (visited[i][j] == 1) continue;
			bfs({ i,j });
		}
	}
	cout << num << "\n";
	
	sort(result.begin(), result.end(), cmp);
	for (int r : result) {
		cout << r << "\n";
	}
}