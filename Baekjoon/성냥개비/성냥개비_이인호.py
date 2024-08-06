import sys
input = sys.stdin.readline

dp = [float('inf')]*101
maxdp =[""]*101

n = int(input())

arr = [6,2,5,10,4,10,10,3,7,10]
arr2 = [2,3,4,5,6,7]

primary = ["", "", "1", "7", "4", "2", "6", "8"]


for i in range(2, 8) :
    dp[i] = primary[i]
for i in range(8, 101) :
    for j in range(2, i-1) :
        dp[i] = min(dp[i], int(str(dp[j]) + str(dp[i-j])))
        if j == 6 :
            dp[i] = min(dp[i], int(str(dp[i-j]) + '0'))




for k in range(101):
    max = ""

    if k == 2:
        maxdp[k] = "1"
    elif k == 3:
        maxdp[k] ="7"
    else:
        t = k // 2
        if  k % 2 ==1:
            max ="7"+"1" * (t-1)
            maxdp[k]= max
        else:
            max = "1"*t
            maxdp[k] = max

for i in range(n):
    k = int(input())
    print(dp[k], maxdp[k])

