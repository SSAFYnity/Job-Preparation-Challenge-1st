from heapq import heappush,heappop
def solution(D:int,graph:list[tuple[int]])->int:
    value = D
    check = dict()
    check[0] = 0
    total = [(0,0)]
    total.append((0,0))
    while total:
        node,dist = heappop(total)
        if check[node] != dist: continue
        for s,e,l in graph:
            if s < node: continue
            next_dist = s-node+dist+l
            if check.get(e,D) > next_dist:
                check[e] = next_dist
                heappush(total,(e,next_dist))
        value = min(value,D+dist-node)
    return value

N,D = map(int,input().split())
graph = list()
for _ in range(N):
    s,e,l = map(int,input().split())
    if e > D: continue
    if e-s<=l:continue
    graph.append((s,e,l))
ans = solution(D,graph)
print(ans)