def dfs(v):
  visited[v] = True

  if v == 99:
    return True

  for w in graph[v]:
    if not visited[w]:
      if dfs(w):
        return True
  
  return False


for _ in range(10):
  index, n = map(int, input().split())
  vertex = list(map(int, input().split()))

  graph = [[] for _ in range(100)]
  visited = [False] * 100
  for i in range(0, len(vertex), 2):
    graph[vertex[i]].append(vertex[i+1])

  print(f'#{index}', end=' ')
  if dfs(0):
    print(1)
  else:
    print(0)
  
