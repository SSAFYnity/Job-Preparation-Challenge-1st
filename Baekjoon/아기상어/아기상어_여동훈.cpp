#include<iostream>
#include<queue>
using namespace std;

int N;
int map[20][20];
struct shark_info {
	int y, x, ate, size;
};
struct target_info {
	int y, x, dist;
};
struct queue_info {
	int y, x, dist;
};
shark_info shark;
target_info fish;

int dist = 0;
void init() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
			if (map[i][j] == 9) { // 상어 정보 초기화
				shark.y = i;
				shark.x = j;
				shark.ate = 0;
				shark.size = 2;
			}
		}
	}
}
int bfs(int target_y, int target_x) {
	const int dy[] = {-1,1,0,0};
	const int dx[] = {0,0,-1,1};

	int dist = 1e9;
	int visited[20][20] = { 0 };
	queue<queue_info> q;
	q.push({ shark.y,shark.x,0 });

	while (!q.empty()) {
		queue_info now = q.front(); q.pop();

		if (now.y == target_y && now.x == target_x) {
			dist = now.dist;
			break;
		}

		for (int i = 0; i < 4; i++) {
			int ny = now.y + dy[i];
			int nx = now.x + dx[i];
			if (ny >= N || nx >= N || ny < 0 || nx < 0) continue;
			if (map[ny][nx] > shark.size) continue;
			if (visited[ny][nx] == 1) continue;
			visited[ny][nx] = 1;
			q.push({ ny,nx,now.dist + 1 });
		}
	}
	return dist;

}
bool find_fish() { // 경로상 먹을 수 있는 물고기 찾기 (fish data 최신화)
	int minV = 1e9;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] != 0 && map[i][j] != 9 && map[i][j] < shark.size) {
				int dist = bfs(i, j);
				if (dist < minV) { // 최솟값 갱신
					minV = dist;
					fish.y = i;
					fish.x = j;
					fish.dist = minV;
				}
			}
		}
	}
	if (minV == 1e9) {
		return false;
	}
	else {
		return true;
	}

}
void eat_fish() {
	// 맵 최신화
	map[shark.y][shark.x] = 0;
	map[fish.y][fish.x] = 9;
	// shark 위치 이동
	shark.y = fish.y;
	shark.x = fish.x;
	shark.ate++;
	if (shark.ate == shark.size) { // 상어 크기 반영
		shark.ate = 0;
		shark.size++;
	}
}

int main() {
	init();
	int T =0;
	while (1) {
		if (!find_fish()) { // 먹을수 있는 물고기 존재 x
			break;
		}
		eat_fish();
		T += fish.dist;
	}
	cout << T;

}