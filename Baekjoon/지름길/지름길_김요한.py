import sys
import heapq

N, D = map(int, sys.stdin.readline().strip().split())

# 주어진 입력값에 따른 그래프 딕셔너리 생성
graph = {_: {_ + 1: 1} for _ in range(D + 1)}
# 도착 지점보다 더 나가는 길은 고려하지 않음
graph[D] = {}
for _ in range(N):
    u, v, w = map(int, sys.stdin.readline().strip().split())
    # 도착 지점보다 더 먼 노드는 제외
    # 지름길이 오히려 더 먼 경우 제외
    if v <= D and w < (v - u):
        if v in graph[u]:
            graph[u][v] = min(graph[u][v], w, v - u)
        else:
            graph[u][v] = w

# dijkstra
queue = list()
heapq.heappush(queue, (0, 0))
distances = {_: float('inf') for _ in graph}
distances[0] = 0
while queue:
    cur_d, cur_v = heapq.heappop(queue)
    if cur_d <= distances[cur_v]:
        for neighbor, weight in graph[cur_v].items():
            distance = cur_d + weight
            if distances[neighbor] > distance:
                distances[neighbor] = distance
                heapq.heappush(queue, (distance, neighbor))

print(distances[D])


