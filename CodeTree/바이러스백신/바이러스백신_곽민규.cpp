#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

#define MAX 1e9

using namespace std;

int N, M, ans = MAX, vTotal = 0, delta[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
vector<pair<int, int> > hospitals;
vector<bool> selected;
vector<vector<int> > region;
vector<vector<bool> > visited;

queue<pair<int, int> > q;

bool isIn(int r, int c) {
    return 0 <= r && r < N && 0 <= c && c < N;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;
    region = vector(N, vector<int>(N));

    for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
            cin >> region[r][c];
            if (region[r][c] == 2) hospitals.push_back({r, c});
            if (region[r][c] == 0) vTotal++;
        }
    }

    for (int i = 0; i < hospitals.size(); i++) {
        selected.push_back(i + M >= hospitals.size());
    }

    do {
        q = queue<pair<int, int> >();
        visited = vector(N, vector(N, false));
        for (int i = 0; i < hospitals.size(); i++) {
            if (!selected[i]) continue;
            q.push(hospitals[i]);
            visited[hospitals[i].first][hospitals[i].second] = true;
        }

        for (int time = 0, vFound = 0; !q.empty(); time++) {
            for (int s = q.size(); s > 0; s--) {
                int r = q.front().first, c = q.front().second;
                q.pop();
                if (region[r][c] == 0) vFound++;
                for (int d = 0; d < 4; d++) {
                    int nr = r + delta[d][0], nc = c + delta[d][1];
                    if (!isIn(nr, nc) || region[nr][nc] == 1 || visited[nr][nc]) continue;
                    q.push({nr, nc});
                    visited[nr][nc] = true;
                }
            }

            if (vFound == vTotal) {
                ans = min(ans, time);
                break;
            }
        }
    } while (next_permutation(selected.begin(), selected.end()));
    cout << (ans == MAX ? -1 : ans);
}
