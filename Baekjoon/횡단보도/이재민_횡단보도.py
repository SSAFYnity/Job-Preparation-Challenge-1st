from heapq import heappush,heappop
import sys
input = sys.stdin.readline

def solution(N:int,M:int,graph:list[dict])->int:
    pri_que = [(0,1)]
    visit = [-1]*(N+1)
    visit[1] = 0
    
    while pri_que:
        time,node = heappop(pri_que)
        if visit[node] < time: continue
        cycle,turn = divmod(time,M)
        default_time = cycle * M
        for next_node,next_turn in graph[node]:
            next_time = default_time + next_turn
            if turn > next_turn: next_time += M
            if visit[next_node] != -1 and next_time > visit[next_node]: continue
            visit[next_node] = next_time
            heappush(pri_que,(next_time,next_node))
    return visit[N]

N,M = map(int,input().split())
graph = [[] for _ in range(N+1)]
for i in range(M):
    a,b = map(int,input().split())
    graph[a].append((b,i+1))
    graph[b].append((a,i+1))
ans = solution(N,M,graph)
print(ans)