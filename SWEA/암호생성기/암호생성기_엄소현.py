from collections import deque

def remove(n):
  num = dq.popleft() - n

  if num <= 0:
    dq.append(0)
    return False
  else:
    dq.append(num)
    return True


for _ in range(10):
  index = int(input())
  dq = deque(list(map(int, input().split())))

  n = 1
  while True:
    if not remove(n):
      break
    n = n % 5 + 1
  
  print(f'#{index}', end=' ')
  print(*dq)
