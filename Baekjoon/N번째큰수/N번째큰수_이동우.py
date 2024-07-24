import sys
input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]
cnt = [N - 1] * N
line = arr[-1]
n = 0
while True:
    n += 1
    if n == N:
        print(max(line))
        break
    i = line.index(max(line))
    cnt[i] -= 1
    line[i] = arr[cnt[i]][i]