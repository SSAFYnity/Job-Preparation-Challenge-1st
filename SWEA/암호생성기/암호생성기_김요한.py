from collections import deque

for _ in range(10):

    T = int(input())
    arr = deque(list(map(int, input().split())))

    num2 = 1
    while arr[-1]:

        num1 = arr.popleft()

        if num1 - num2 >= 0:
            arr.append(num1 - num2)
        else:
            arr.append(0)

        if num2 == 5:
            num2 = 1
        else:
            num2 += 1

    ans = [str(_) for _ in arr]

    print(f'#{T} {" ".join(ans)}')
