def solution(L,info,start_node,end_node,max_node):
    graph = [[] for _ in range(max_node)]
    for i in range(L):
        A,B = info[2*i],info[2*i+1]
        graph[A].append(B)
        visit = [0]*max_node
        visit[start_node] = 1
    ls = [start_node]
    while ls:
        node = ls.pop()
        for next_node in graph[node]:
            if not visit[next_node]:
                visit[next_node] = 1
                ls.append(next_node)
    return visit[end_node]
 
T = 10
start_node = 0
end_node = 99
max_node = 100
result = list()
for _ in range(T):
    tc,L = map(int,input().split())
    info = list(map(int,input().split()))
    result.append(f'#{tc} {solution(L,info,start_node,end_node,max_node)}')
print('\n'.join(result))