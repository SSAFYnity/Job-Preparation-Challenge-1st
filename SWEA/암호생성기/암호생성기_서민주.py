def cycle():
    for i in range(1, 6):
        q = arr.pop(0)-i
        if q>0:
            arr.append(q)
        else:
            arr.append(0)
            break


for _ in range(10):
    tc = int(input())
    arr = list(map(int, input().split()))

    while True:
        cycle()
        if arr[-1]==0:
            break

    print(f'#{tc} {" ".join(map(str, arr))}')