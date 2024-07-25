# 길찾기 - 양희제

# 단방향 그래프
def dfs(v):
    visited[v] = 1
    while True:
        for w in adj_list[v]:
            if visited[w] == 0:
                stack.append(v)
                visited[w] = 1
                v = w
                break
        else:
            if stack:
                v = stack.pop()
            else:
                break


for _ in range(1, 11):

    tc, N = map(int, input().split())
    nodes = list(map(int, input().split()))
    adj_list = [[] for _ in range(100)]
    visited = [0] * 100
    stack = []
    is_connected = 1

    for i in range(N):
        adj_list[nodes[i * 2]].append(nodes[i * 2 + 1])

    dfs(0)

    if not visited[99]:
        is_connected = 0

    print(f'#{tc} {is_connected}')
