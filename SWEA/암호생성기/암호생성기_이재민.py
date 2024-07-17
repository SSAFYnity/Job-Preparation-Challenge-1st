from collections import deque

def solution(que):
    i = 1
    while que[-1] != 0:
        que[0] = max(que[0]-i,0)
        que.rotate(-1)
        i = i%5+1
    return que

T = 10
for _ in range(T):
    tc = input()
    que = deque(map(int,input().split()))
    ans = solution(que)
    print(f'#{tc}',end=' ')
    print(*ans)