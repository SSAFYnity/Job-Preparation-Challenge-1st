from collections import Counter

n, m = map(int, input().split())
arr = list(map(int, input().split()))

cnt = Counter()

start, end = 0, 0
answer = 0

while end < n:
    if cnt[arr[end]] < m:
        cnt[arr[end]] += 1
        end += 1
        answer = max(answer, end - start)
    else:
        cnt[arr[start]] -= 1
        start += 1

print(answer)