for tc in range(10):
    tc, E = map(int, input().split())
    arr = list(map(int, input().split()))
    go = [[] for _ in range(100)]

    # 인접 리스트 만들기
    for i in range(E):
        start = arr[i*2]
        end = arr[i*2 + 1]
        go[start].append(end)
    

    visited = [0]*100 # 방문표시
    stack = [0]; # 초기값 넣기

    while stack: # 스택 빌때까지 왔던 길 넣기
        now = stack.pop() # 현재 위치
        if not visited[now]: # 방문한 적이 없다면
            visited[now] = 1 # 방문 표시

            for v in go[now]: # 현재 위치에서 갈 수 있는 인접 리스트 순회
                if not visited[v]: # 방문한 적 없다면
                    stack.append(v) # 스택에 넣기

    if visited[99]: # B위치 도달햇으면
        result = 1
    else: # 도달못했으면
        result = 0
    print("#{} {}".format(tc, result))