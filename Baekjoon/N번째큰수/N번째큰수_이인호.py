import heapq

hq = []
n = int(input())

for i in range(n):
    arr= map(int,input().split())
    for k in arr:
        if len(hq) < n:
            heapq.heappush(hq,k)
        else:
            heapq.heappush(hq,k)
            heapq.heappop(hq)

print(hq[0])