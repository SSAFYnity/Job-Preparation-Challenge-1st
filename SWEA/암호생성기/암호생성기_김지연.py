for _ in range(10):
    tc = input()
    q = list(map(int, input().split()))

    i = 0
    while True:
        tmp = q.pop(0) - (i + 1)
        if tmp <= 0:
            q.append(0)
            break
        q.append(tmp)
        i = (i + 1) % 5
    
    print(f'#{tc} ', end='')
    print(*q, end=' ')
    print('')