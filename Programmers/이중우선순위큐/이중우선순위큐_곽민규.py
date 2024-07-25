import heapq

def solution(operations):
    heap = []
    answer = []
    for o in operations:
        current = o.split()
        if current[0] == 'I':
            heapq.heappush(heap,int(current[1]))
        elif current[0] == 'D' and heap != []:
            if current[1] == '-1':
                heapq.heappop(heap)
            else:
                heap = heapq.nlargest(len(heap),heap)[1:]
                heapq.heapify(heap)
    if heap == [] :
        answer = [0,0]
    else:
        answer.append(heapq.nlargest(1,heap)[0])
        answer.append(heapq.heappop(heap))
    return answer