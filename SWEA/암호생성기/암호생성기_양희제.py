# 암호생성기 - 양희제

for _ in range(1, 11):
    tc = int(input())
    queue = list(map(int, input().split()))

    while True:
        is_password = False
        for i in range(1, 6):
            queue.append(queue.pop(0) - i)
            if queue[-1] <= 0:
                queue[-1] = 0
                is_password = True
                break
        if is_password:
            break

    print(f"#{tc}", *queue)
