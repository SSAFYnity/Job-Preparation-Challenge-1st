N, K = map(int, input().split())
A = list(map(int, input().split()))
cnt_dict = {key: 0 for key in set(A)}
l = 0
r = 0
ans = 0
full = False
while l < N:
    if full or r == N:
        cnt_dict[A[l]] -= 1
        if cnt_dict[A[l]] == K:
            full = False
        l += 1
    else:
        cnt_dict[A[r]] += 1
        if cnt_dict[A[r]] == K + 1:
            full = True
        r += 1
    if not full and r - l > ans:
        ans = r - l
print(ans)