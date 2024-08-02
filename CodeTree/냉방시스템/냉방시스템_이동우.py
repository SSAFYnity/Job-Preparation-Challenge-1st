import sys
input = sys.stdin.readline

n, m, kk = map(int, input().split())
grid = []
for _ in range(n):
    line = list(map(int, input().split()))
    grid.append(line)
walls = [[], []]
for _ in range(m):
    x, y, s = map(int, input().split())
    walls[s].append([x - 1, y - 1])
add_cool = [[0] * n for _ in range(n)]
for i in range(n):
    for j in range(n):
        visited = [[False] * n for _ in range(n)]
        if grid[i][j] == 2:
            visited[i][j] = True
            for k in range(5):
                s = 5 - k
                y = j - k - 1
                if y < 0:
                    break
                for x in range(i - k, i + k + 1):
                    if x < 0 or x >= n:
                        continue
                    if [x, y + 1] in walls[1]:
                        continue
                    if visited[x][y + 1] == True or (x != 0 and visited[x - 1][y + 1] == True and [x, y + 1] not in walls[0]) or (x != n - 1 and visited[x + 1][y + 1] == True and [x + 1, y + 1] not in walls[0]):
                        add_cool[x][y] += s
                        visited[x][y] = True
        if grid[i][j] == 3:
            visited[i][j] = True
            for k in range(5):
                s = 5 - k
                x = i - k - 1
                if x < 0:
                    break
                for y in range(j - k, j + k + 1):
                    if y < 0 or y >= n:
                        continue
                    if [x + 1, y] in walls[0]:
                        continue
                    if visited[x + 1][y] == True or (y != 0 and visited[x + 1][y - 1] == True and [x + 1, y] not in walls[1]) or (y != n - 1 and visited[x + 1][y + 1] == True and [x + 1, y + 1] not in walls[1]):
                        add_cool[x][y] += s
                        visited[x][y] = True
        if grid[i][j] == 4:
            visited[i][j] = True
            for k in range(5):
                s = 5 - k
                y = j + k + 1
                if y >= n:
                    break
                for x in range(i - k, i + k + 1):
                    if x < 0 or x >= n:
                        continue
                    if [x, y] in walls[1]:
                        continue
                    if visited[x][y - 1] == True or (x != 0 and visited[x - 1][y - 1] == True and [x, y - 1] not in walls[0]) or (x != n - 1 and visited[x + 1][y - 1] == True and [x + 1, y - 1] not in walls[0]):
                        add_cool[x][y] += s
                        visited[x][y] = True
        if grid[i][j] == 5:
            visited[i][j] = True
            for k in range(5):
                s = 5 - k
                x = i + k + 1
                if x >= n:
                    break
                for y in range(j - k, j + k + 1):
                    if y < 0 or y >= n:
                        continue
                    if [x, y] in walls[0]:
                        continue
                    if visited[x - 1][y] == True or (y != 0 and visited[x - 1][y - 1] == True and [x - 1, y] not in walls[1]) or (y != n - 1 and visited[x - 1][y + 1] == True and [x - 1, y + 1] not in walls[1]):
                        add_cool[x][y] += s
                        visited[x][y] = True
t = 0
cool_map = [[0] * n for _ in range(n)]
while t < 100:
    for i in range(n):
        for j in range(n):
            cool_map[i][j] += add_cool[i][j]
    mix_cool = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if i != 0 and [i, j] not in walls[0]:
                chai = cool_map[i][j] - cool_map[i - 1][j]
                if chai > 0:
                    mix_cool[i - 1][j] += chai // 4
                    mix_cool[i][j] -= chai // 4
                else:
                    chai = -chai
                    mix_cool[i][j] += chai // 4
                    mix_cool[i - 1][j] -= chai // 4
            if j != 0 and [i, j] not in walls[1]:
                chai = cool_map[i][j] - cool_map[i][j - 1]
                if chai > 0:
                    mix_cool[i][j - 1] += chai // 4
                    mix_cool[i][j] -= chai // 4
                else:
                    chai = -chai
                    mix_cool[i][j] += chai // 4
                    mix_cool[i][j - 1] -= chai // 4
    for i in range(n):
        for j in range(n):
            cool_map[i][j] += mix_cool[i][j]
    for i in range(n):
        for j in range(n):
            if (i == 0 or i == n - 1 or j == 0 or j == n - 1) and cool_map[i][j]:
                cool_map[i][j] -= 1
    t += 1
    ans = True
    for i in range(n):
        for j in range(n):
            if grid[i][j] == 1 and cool_map[i][j] < kk:
                ans = False
                break
        if not ans:
            break
    if ans:
        break
if t == 100:
    t = -1
print(t)