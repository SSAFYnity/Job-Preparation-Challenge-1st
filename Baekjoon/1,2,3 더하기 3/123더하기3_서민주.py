dp = [0, 1, 2, 4]
n = int(input())
for i in range(n):
    a = int(input())
    for j in range(len(dp), a+1):
        dp.append((dp[-3]+dp[-2]+dp[-1])%1000000009)
    print(dp[a])