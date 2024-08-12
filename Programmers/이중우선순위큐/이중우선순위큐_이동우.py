import heapq

def solution(operations):
    min_heap = []
    max_heap = []
    heap_dict = dict()
    for oprt in operations:
        DI, n = oprt.split()
        n = int(n)
        if DI == 'I':
            heapq.heappush(min_heap, n)
            heapq.heappush(max_heap, -n)
            if n in heap_dict.keys():
                heap_dict[n] += 1
            else:
                heap_dict[n] = 1
        elif DI == 'D':
            if n == -1 and min_heap:
                m = heapq.heappop(min_heap)
                while heap_dict[m] == 0 and min_heap:
                    m = heapq.heappop(min_heap)
                if heap_dict[m]:
                    heap_dict[m] -= 1
            elif n == 1 and max_heap:
                m = -heapq.heappop(max_heap)
                while heap_dict[m] == 0 and max_heap:
                    m = -heapq.heappop(max_heap)
                if heap_dict[m]:
                    heap_dict[m] -= 1
                    
    answer = [0, 0]
    if min_heap:
        m = heapq.heappop(min_heap)
        while heap_dict[m] == 0 and min_heap:
            m = heapq.heappop(min_heap)
        if heap_dict[m]:
            answer[1] = m
    if max_heap:
        m = -heapq.heappop(max_heap)
        while heap_dict[m] == 0 and max_heap:
            m = -heapq.heappop(max_heap)
        if heap_dict[m]:
            answer[0] = m
        
    return answer