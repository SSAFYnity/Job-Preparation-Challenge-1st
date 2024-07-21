#include <iostream>
#include <deque>
#include <vector>
#include <tuple>
using namespace std;

const int MX = 2100000000;
int d[4][2] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
int n, m;
int region[51][51];
int visited[51][51];
int totalVirus = 0;
vector<pair<int, int>> hospitals;
bool selected[10];
int exploreCount = 1;
deque<tuple<int, int, int>> dq;
int minTime = MX;
int temp[51][51];

int explore() {
    int catchVirus = 0;
    int maxTime = 0;

    while (!dq.empty()) {
        int curr, curc, time;
        tie(curr, curc, time) = dq.front(); dq.pop_front();

        for (int i = 0; i < 4; i++) {
            int r = curr + d[i][0];
            int c = curc + d[i][1];

            if (r < 0 || r >= n || c < 0 || c >= n) {
                continue;
            }

            if (region[r][c] == 1) {
                continue;
            }

            if (visited[r][c] == exploreCount) {
                continue;
            }

            if (region[r][c] == 0) {
                maxTime = max(maxTime, time + 1);
                catchVirus++;
            }
            temp[r][c] = time + 1;
            dq.push_back({r, c, time + 1});
            visited[r][c] = exploreCount;
        }
    }

    if (catchVirus == totalVirus) {
        return maxTime;
    }
    return MX;
}

void pickHospitals(int depth, int start) {
    if (depth == m) {
        for (int i = 0; i < hospitals.size(); i++) {
            if (selected[i]) {
                dq.push_back({hospitals[i].first, hospitals[i].second, 0});
                visited[hospitals[i].first][hospitals[i].second] = exploreCount;
            }
        }
        minTime = min(minTime, explore());
        exploreCount++;
        return;
    }

    for (int i = start; i < hospitals.size(); i++) {
        if (selected[i]) {
            continue;
        }
        selected[i] = true;
        pickHospitals(depth + 1, i + 1);
        selected[i] = false;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> region[i][j];
            if (region[i][j] == 0) {
                totalVirus++;
                continue;
            }
            if (region[i][j] == 2) {
                hospitals.push_back({i, j});
            }
        }
    }

    if (totalVirus == 0) {
        cout << 0;
        return 0;
    }

    pickHospitals(0, 0);
    cout << (minTime == MX ? -1 : minTime);

    return 0;
}