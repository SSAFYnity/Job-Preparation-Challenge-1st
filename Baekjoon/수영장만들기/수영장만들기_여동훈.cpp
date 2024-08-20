#include<iostream>
#include<queue>
#include<string.h>
using namespace std;

struct info {
	int y, x;
};
int N, M;
int result;
char map[50][50];
int visited [50][50];
int successed_visited[50][50];
void init() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> map[i];
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			map[i][j] -= '0';
		}
	}
	result = 0;
}
int bfs(int y, int x, int max_height) {
	memset(visited, 0, sizeof(visited));
	memcpy(visited, successed_visited, sizeof(visited));
	const int dy[] = {-1,1,0,0};
	const int dx[] = {0,0,-1,1};
	int sum = 0;
	queue<info> q;
	q.push({y,x});
	visited[y][x] = 1;
	sum += (max_height - map[y][x]);

	while (!q.empty()) {
		info now = q.front(); q.pop();
		for (int i = 0; i < 4; i++) {
			int ny = now.y + dy[i];
			int nx = now.x + dx[i];
			if (ny < 0 || nx < 0 || ny >= N || nx >= M) return 0;// 수영장 못만들면 아예 종료 시켜버림
			if (visited[ny][nx] == 1) continue;
			if (map[ny][nx] >= max_height) continue;
			visited[ny][nx] = 1;
			q.push({ ny,nx });
			sum += (max_height - map[ny][nx]);
		}
	}
	memcpy(successed_visited, visited, sizeof(visited));
	return sum; // 결과 값이 나왔다면 방문한 곳 visited 고정 처리
}
void solve() {
	for (int max_height = 9; max_height > 1; max_height--) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (successed_visited[i][j] == 1) continue;
				if (map[i][j] >= max_height) continue;
				int tmp_sum = bfs(i,j,max_height);
				result += tmp_sum;
			}
		}
	}
	cout << result; 
}
int main() {
	init();
	solve();

}