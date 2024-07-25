from collections import deque

N, M = map(int, input().split())
MAP = [list(map(int, input().split())) for _ in range(N)]

hospital = []
comb = []
for i in range(N):
    for j in range(N):
        if MAP[i][j] == 2:
            hospital.append((i, j))

def dfs(start, stack):
    if len(stack) == M:
        comb.append(stack[:])
        return
    for k in range(start, len(hospital)):
        stack.append(hospital[k])
        dfs(k + 1, stack)
        stack.pop()

dfs(0, [])

direct = [(-1, 0), (1, 0), (0, -1), (0, 1)]

min_distance = float("inf")

for c in comb:
    q = deque()
    q.extend(c)
    visited = [[-1] * N for _ in range(N)]

    for x, y in c:
        visited[x][y] = 0

    while q:
        x, y = q.popleft()

        for dx, dy in direct:
            nx, ny = x + dx, y + dy
            if 0 <= nx < N and 0 <= ny < N and visited[nx][ny] == -1 and MAP[nx][ny] != 1:
                visited[nx][ny] = visited[x][y] + 1
                q.append((nx, ny))

    max_distance = 0
    for i in range(N):
        for j in range(N):
            if MAP[i][j] == 0 and visited[i][j] == -1:
                max_distance = float("inf")
            elif MAP[i][j] == 0:
                max_distance = max(max_distance, visited[i][j])

    min_distance = min(min_distance, max_distance)

print(min_distance if min_distance != float("inf") else -1)
