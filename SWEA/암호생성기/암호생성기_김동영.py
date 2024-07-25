from collections import deque

for _ in range(10):
    T = int(input())
    lst = list(map(int, input().split()))

    q = deque(lst)
    flag = True

    while flag:
        for i in range(1, 6):
            if not flag:
                break
            a = q.popleft()
            a -= i
            if a <= 0:
                a = 0
                flag = False
            q.append(a)

    print(f"#{T}", *q)
