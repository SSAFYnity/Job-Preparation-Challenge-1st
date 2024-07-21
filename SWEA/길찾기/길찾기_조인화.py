from collections import deque

def bfs():
    q = deque()
    visited = [False] * 100
    q.append(0)
    visited[0] = True
    while q:
        v = q.popleft()
        for w in next_nodes[v]:
            if not visited[w]:
                if w == 99:
                    return 1
                q.append(w)
                visited[w] = True

    return 0


for _ in range(10):
    tc, E = map(int, input().split())
    lst = list(map(int, input().split()))
    next_nodes = [[] for _ in range(100)]
    for i in range(E):
        next_nodes[lst[i*2]].append(lst[i*2+1])

    print(f'#{tc} {bfs()}')