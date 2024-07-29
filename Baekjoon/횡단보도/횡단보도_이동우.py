import heapq
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
cross = [dict() for _ in range(N + 1)]
for i in range(M):
    A, B = map(int, input().split())
    if B in cross[A].keys():
        cross[A][B].append(i + 1)
    else:
        cross[A][B] = [i + 1]
    if A in cross[B].keys():
        cross[B][A].append(i + 1)
    else:
        cross[B][A] = [i + 1]
visited = [[100000, 0] for _ in range(N + 1)]
visited[1][0] = 0
hq = [[0, 0, 1]]
while hq:
    _, _, S = heapq.heappop(hq)
    if visited[S][0] > visited[-1][0] or (visited[S][0] == visited[-1][0] and visited[S][1] > visited[-1][1]):
        break
    for E, order in cross[S].items():
        E_stat = visited[E][:]
        for i in order:
            new_stat = visited[E][:]
            new_stat[1] = i
            if visited[S][1] <= i:
                new_stat[0] = visited[S][0]
            if visited[S][1] > i:
                new_stat[0] = visited[S][0] + 1
            if new_stat[0] < E_stat[0] or (new_stat[0] == E_stat[0] and new_stat[1] < E_stat[1]):
                E_stat = new_stat[:]
        if visited[E] != E_stat[:]:
            visited[E] = E_stat[:]
            heapq.heappush(hq, [visited[E][0], visited[E][1], E])
print(visited[-1][0] * M + visited[-1][1])