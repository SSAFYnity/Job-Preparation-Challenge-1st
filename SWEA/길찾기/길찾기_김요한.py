for _ in range(10):

    ans = 0
    test, N = map(int, input().split())
    arr = list(map(int, input().split()))
    graph = {_: [] for _ in range(N-1)}
    graph[99] = []

    for i in range(0, N * 2, 2):
        graph[arr[i]].append(arr[i + 1])

    visited = set()

    stack = [0]
    while stack:
        v = stack.pop()
        if v == 99:
            ans = 1
            break
        if v not in visited:
            stack += graph[v]
            visited.add(v)

    print(f'#{test} {ans}')