def dfs(start):
    visited = [0] * (100)
    stack = [start]

    while stack:
        # 경로 상에 마지막에 방문한 도시에서 길찾기
        top = stack[-1]
        # 현재 도시에 방문했다는 표시
        visited[top] = 1

        # 다음 갈 도시 찾기
        for i in range(100):
            # 다음 갈 도시로 길이 있다면 방문
            if adj[top][i] == 1 and not visited[i]:
                stack.append(i)
                # B 도시 갔으면 경로가 있다는 것
                if i == 99:
                    return 1
                break
        # 방문할 곳이 없어서 뒤로 돌아간다
        else:
            stack.pop()
    # 모든 방문경로를 찾았으나 B 도시로 못감
    return 0

for i in range(10):
    tc, E = map(int, input().split())
    edges = list(map(int, input().split()))

    adj = [[0] * 100 for _ in range(100)]

    # 돌아가는 길 없으므로 연결 경로 하나만 지정
    for j in range(0, E * 2, 2):
        adj[edges[j]][edges[j + 1]] = 1

    result = dfs(0)

    print(f'#{tc} {result}')