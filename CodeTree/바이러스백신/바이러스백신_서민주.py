from itertools import combinations
from collections import deque

def bfs(hospitals):
    visited = [[-1] * n for _ in range(n)]
    q = deque()
    for y, x in hospitals:
        q.append((y, x))
        visited[y][x] = 0
        
    max_time = 0
    
    while q:
        y, x = q.popleft()
        for dy, dx in ((0, -1), (-1, 0), (0, 1), (1, 0)):
            ny, nx = dy + y, dx + x
            if 0 <= ny < n and 0 <= nx < n and arr[ny][nx] != 1 and visited[ny][nx] == -1:
                visited[ny][nx] = visited[y][x] + 1
                q.append((ny, nx))
                if arr[ny][nx] == 0:
                    max_time = max(max_time, visited[ny][nx])
                    
    for i in range(n):
        for j in range(n):
            if arr[i][j] == 0 and visited[i][j] == -1:
                return float('inf')

    return max_time

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]

dots = [(i, j) for i in range(n) for j in range(n) if arr[i][j] == 2]
min_time = float('inf')

for hospitals in combinations(dots, m):
    time = bfs(hospitals)
    min_time = min(min_time, time)

print(min_time if min_time != float('inf') else -1)