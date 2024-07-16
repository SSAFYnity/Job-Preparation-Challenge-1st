from collections import deque

for _ in range(10):
    T, N = map(int, input().split())
    MAP = [[] for _ in range(100)]

    lst = list(map(int, input().split()))

    for i in range(N):
        a, b = lst[2*i], lst[2*i + 1]
        MAP[a].append(b)

    q = deque()
    q.append(0)
    visited = [False] * 100

    while q:
        v = q.popleft()

        if not visited[v]:
            visited[v] = True

            for n in MAP[v]:
                if not visited[n]:
                    q.append(n)

    result = 1 if visited[99] else 0
    print(f"#{T}", result)

