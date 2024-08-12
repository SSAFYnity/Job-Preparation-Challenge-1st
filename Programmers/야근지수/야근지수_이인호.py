import heapq

def solution(n, works):
    answer = 0
    if sum(works) <= n:
        answer = 0
    else :
        works =  [-work for work in works]
        heapq.heapify(works)
        for i in range(n):
            work = heapq.heappop(works) + 1
            heapq.heappush(works,work)
        for i in range(len(works)):
            answer += (works[i]*works[i])
        print(works)
    return answer

