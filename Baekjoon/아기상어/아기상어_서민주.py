from collections import deque

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]

def bfs(si, sj):
    q = deque()
    visited = [[0]*n for _ in range(n)]
    tlst = []

    q.append((si, sj))
    visited[si][sj] = 1
    eat = 0

    while q:
        # q에서 데이터 한개 꺼냄
        ci, cj = q.popleft()
        if eat==visited[ci][cj]:
            return tlst, eat-1

        # 4방향 범위 내, 미방문, 조건(나보다 같거나 작은 물고기)
        for di, dj in ((-1, 0), (0, 1), (0, -1), (1, 0)):
            ni, nj = ci+di, cj+dj
            if 0<=ni<n and 0<=nj<n and visited[ni][nj]==0 and shark>=arr[ni][nj]:
                q.append((ni, nj))
                visited[ni][nj] = visited[ci][cj] + 1
                # 나보다 작은 물고기인 경우, tlst에 추가
                if shark > arr[ni][nj] > 0:
                    tlst.append((ni, nj))
                    eat = visited[ni][nj]

    # 못 찾는 경우
    return tlst, eat-1

for i in range(n):
    for j in range(n):
        if arr[i][j]==9:
            ci, cj = i, j
            arr[i][j] = 0

shark = 2
cnt = 0
ans = 0
while True:
    tlst, dist = bfs(ci, cj)
    # 더 이상 먹을 물고기가 없는 경우
    if len(tlst)==0:
        break
    tlst.sort(key=lambda x: (x[0], x[1]))
    ci, cj = tlst[0]
    # 물고기 먹기
    arr[ci][cj] = 0
    cnt += 1
    ans += dist
    # 크기만큼 물고기 먹은 경우 크기 추가가
    if shark==cnt:
        shark+=1
        cnt=0

print(ans)