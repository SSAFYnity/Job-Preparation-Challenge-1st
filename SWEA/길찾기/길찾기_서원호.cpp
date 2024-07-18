#include<iostream>
using namespace std;

int path[100][2];
bool visited[100];
bool reached;

void visit(int cur) {
    if (cur == 99) {
        reached = true;
        return;
    }
    
    for (int i = 0; i < 2; i++) {
        if (path[cur][i] == -1) {
            break;
        }
        if (visited[path[cur][i]]) {
            continue;
        }
        visited[path[cur][i]] = true;
        visit(path[cur][i]);
        visited[path[cur][i]] = false;
    }
}

int main(int argc, char** argv) {
	for(int test_case = 1; test_case <= 10; ++test_case) {
        for (int i = 0; i < 100; i++) {
            path[i][0] = -1;
            path[i][1] = -1;
            visited[i] = false;
        }
        int c, count;
        cin >> c >> count;
        reached = false;
        for (int i = 0; i < count; i++) {
            int s, e;
            cin >> s >> e;
            if (path[s][0] == -1) {
                path[s][0] = e;
                continue;
            }
            path[s][1] = e;
        }
        
        visit(0);
        cout << '#' << c << ' ' << reached << '\n';
	}
	return 0;
}