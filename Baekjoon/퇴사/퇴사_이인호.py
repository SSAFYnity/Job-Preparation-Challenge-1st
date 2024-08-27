

n = int(input())
arr= []
for i in range(n):
    x,y = map(int, input().split())
    arr.append([x,y])
maxmin =0
tmp =0
now =0


def dfs(now, depth, profit):
    global maxmin
    if depth == n:

        maxmin = max(maxmin,profit)
        return profit
    if depth > n:
        return
    profit += arr[now][1]

    dfs(now+arr[depth][0],depth+arr[now][0],profit)
    profit -= arr[now][1]
    dfs(now+1, depth+1, profit)

dfs(0,0,0)


print(maxmin)
