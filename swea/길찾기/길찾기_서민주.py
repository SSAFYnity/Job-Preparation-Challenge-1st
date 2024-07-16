def dfs(num):
    if num == 99:
        return 1
    visited[num] = 1
    for i in road[num]:
        if not visited[i]:
            if dfs(i) == 1:
                return 1
    return 0


for _ in range(10):
    # 테스트 케이스 번호, 길의 총 개수
    tc, roads = map(int, input().split())
    # 순서쌍
    arr = list(map(int, input().split()))

    # 방문 표시
    visited = [0] * 100
    # 경로
    road = [[] for _ in range(100)]
    # 갈수 있는 길 표시
    for i in range(roads):
        road[arr[i * 2]].append(arr[i * 2 + 1])

    result = dfs(0)
    if result == 1:
        print(f'#{tc} {1}')
    else:
        print(f'#{tc} {0}')