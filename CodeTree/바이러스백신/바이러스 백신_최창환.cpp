#include <algorithm>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

struct hospital {
  int x, y;
};

int n, m, arr[50][50], dist[50][50], ans = 21e8, dx[] = {1, 0, -1, 0}, dy[] = {0, 1, 0, -1};
vector<vector<int> > combination;
vector<hospital> v;
queue<pair<int, int> > q;

void init()
{
  cin >> n >> m;
  int hospitalCnt = 0;
  for(int i = 0; i < n; ++i)
    for(int j = 0; j < n; ++j) {
      cin >> arr[i][j];
      if(arr[i][j] == 2) {
        v.push_back({i, j});
        ++hospitalCnt;
      }
    }

  vector<int> combinationTemp(hospitalCnt);
  vector<int> tempCnt(hospitalCnt);
  for(int i = 0; i < hospitalCnt; ++i)
    combinationTemp[i] = i;
  for(int i = 0; i < m; ++i)
    tempCnt[i] = 1;

  do {
    vector<int> temp;
    for(int i = 0; i < hospitalCnt; ++i)
      if(tempCnt[i] == 1)
        temp.push_back(combinationTemp[i]);
    combination.push_back(temp);
  } while(prev_permutation(tempCnt.begin(), tempCnt.end()));
}

void check()
{
  int what = true;
  int tempDist = 1;
  for(int i = 0; i < n; ++i)
    for(int j = 0; j < n; ++j)
      if(!arr[i][j] && !dist[i][j])
        what = false;
      else if(!arr[i][j] && dist[i][j]) tempDist = max(tempDist, dist[i][j]);
  if(what)
    ans = min(ans, tempDist);
}

void bfs()
{
  while(!q.empty()) {
    pair<int, int> now = q.front();
    q.pop();
    for(int i = 0; i < 4; ++i) {
      int nx = now.first + dx[i];
      int ny = now.second + dy[i];
      if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
      if(arr[nx][ny] == 1 || dist[nx][ny]) continue;
      dist[nx][ny] = dist[now.first][now.second] + 1;
      q.emplace(nx, ny);
    }
  }
}

void run()
{
  for(vector<int>& com: combination) {
    for(int i = 0; i < n; ++i)
      fill(dist[i], dist[i] + n, 0);
    for(int& i: com) {
      q.emplace(v[i].x, v[i].y);
      dist[v[i].x][v[i].y] = 1;
    }
    bfs();
    check();
  }
}

int main()
{
  init();
  run();
  if(ans != 21e8)
    cout << ans - 1;
  else
    cout << -1;
}
