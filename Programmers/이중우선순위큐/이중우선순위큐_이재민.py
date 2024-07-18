from heapq import heappush,heappop
def solution(operations):
    max_que = list()
    min_que = list()
    check = dict()
    for operation in operations:
        order,num = operation.split()
        num = int(num)
        if order == 'I':
            heappush(max_que,-num)
            heappush(min_que,num)
            check[num] = check.get(num,0) + 1
        else:
            if num == 1:
                while max_que and check[-max_que[0]] == 0:
                    heappop(max_que)
                if max_que:
                    check[-heappop(max_que)] -= 1
            else:
                while min_que and check[min_que[0]] == 0:
                    heappop(min_que)
                if min_que:
                    check[heappop(min_que)] -= 1
    while max_que and check[-max_que[0]] == 0:
        heappop(max_que)
    while min_que and check[min_que[0]] == 0:
        heappop(min_que)
    if max_que:
        answer = [-heappop(max_que),heappop(min_que)]
    else:
        answer = [0,0]
    return answer