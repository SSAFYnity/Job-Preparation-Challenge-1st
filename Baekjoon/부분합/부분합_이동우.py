N, S = map(int, input().split())
arr = list(map(int, input().split()))
ans = 0
i = 0
j = 1
hap = arr[0]
while True:
    if j == N:
        if hap >= S:
            if not ans or j - i < ans:
                ans = j - i
            hap -= arr[i]
            i += 1
            if i == N:
                break
        else:
            break
    elif hap < S:
        hap += arr[j]
        j += 1
    else:
        if not ans or j - i < ans:
            ans = j - i
        hap -= arr[i]
        i += 1
print(ans)