def find_fish(lst, visited, mv):
    global sea, shark, is_fish, ans
    if is_fish:
        return
    if not lst:
        return
    move = [[-1,0],[0,-1],[0,1],[1,0]]
    new_lst = []
    for x, y in lst:
        visited[x][y] = True
        for m in move:
            go = [x + m[0], y + m[1]]
            if 0 <= go[0] < N and 0 <= go[1] < N and not visited[go[0]][go[1]] and sea[go[0]][go[1]] <= shark[2]:
                visited[go[0]][go[1]] = True
                new_lst.append((go[0], go[1]))
    new_lst.sort()
    for x, y in new_lst:
        if 0 < sea[x][y] < shark[2]:
            shark[3] -= 1
            if shark[3] == 0:
                shark[2] += 1
                shark[3] = shark[2]
            shark[0] = x
            shark[1] = y
            sea[x][y] = 0
            ans += mv
            is_fish = True
            return
    find_fish(new_lst, visited, mv + 1)

N = int(input())
sea = []
for i in range(N):
    line = list(map(int, input().split()))
    if 9 in line:
        shark = [i, line.index(9), 2, 2]
    sea.append(line)
sea[shark[0]][shark[1]] = 0
is_fish = True
ans = 0
while is_fish:
    is_fish = False
    find_fish([(shark[0], shark[1])], [[False] * N for _ in range(N)], 1)
print(ans)