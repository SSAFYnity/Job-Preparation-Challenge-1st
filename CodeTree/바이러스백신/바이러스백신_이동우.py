from itertools import combinations
import copy

N, M = map(int, input().split())
hospitals = [] # 병원들의 좌표 리스트
city = []
virus_n = 0 # 바이러스의 총 개수
for i in range(N):
    line = list(map(int, input().split()))
    for j in range(N):
        if line[j] == 2:
            hospitals.append([i, j])
        if line[j] == 0:
            virus_n += 1
    city.append(line)
combi_h = list(combinations(hospitals, M)) # 병원 중 M개를 선택하여 조합
ans = 2500 # 최대 값 지정 (N의 최대값인 50*50)

for hospitals in combi_h:
    # DFS
    stack = hospitals
    visited = copy.deepcopy(city)
    for i, j in stack:
        visited[i][j] = 1
    n = virus_n
    cnt = 0
    while stack and n:
        new_stack = []
        for i, j in stack:
            for ii, jj in ([0, 1], [0, -1], [1, 0], [-1, 0]):
                ni = i + ii
                nj = j + jj
                if ni < 0 or ni >= N or nj < 0 or nj >= N:
                    continue
                if visited[ni][nj] == 0:
                    n -= 1
                    new_stack.append([ni, nj])
                    visited[ni][nj] = 1
                elif visited[ni][nj] == 2:
                    new_stack.append([ni, nj])
                    visited[ni][nj] = 1
        stack = new_stack
        cnt += 1
    if n: # 바이러스가 남아있다면 cnt 최대값으로 초기화
        cnt = 2500
    if cnt < ans: # ans 업데이트
        ans = cnt

if ans == 2500: # ans가 그대로 2500이라면 불가능
    print(-1)
else:
    print(ans)