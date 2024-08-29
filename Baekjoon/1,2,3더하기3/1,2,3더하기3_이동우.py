T = int(input())
arr = [1, 1, 2]
i = 3
while i <= 1000000:
    arr.append((arr[-1] + arr[-2] + arr[-3]) % 1000000009)
    i += 1
for _ in range(T):
    n = int(input())
    print(arr[n])