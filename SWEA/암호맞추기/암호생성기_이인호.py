for _ in range(10):
    num = int(input())
    arr = list(map(int, input().split()))
    cnt = 1

    while True:

        arr[0] -= cnt
        tmp = arr[0]
        cnt += 1
        if cnt == 6:
            cnt = 1
        for i in range(7):
            arr[i] = arr[i + 1]
        arr[7] = tmp

        if arr[7] <= 0:
            arr[7] = 0
            break

    print('#' + str(num), end=" ")
    for i in arr:
        print(i, end=" ")
    print()