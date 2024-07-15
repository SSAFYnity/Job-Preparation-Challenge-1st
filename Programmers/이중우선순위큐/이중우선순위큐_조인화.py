import heapq

def solution(operations):

    max_pq = []
    min_pq = []
    max_cnt = {}
    min_cnt = {}
    answer = [0, 0]

    for operation in operations:
        c, d = operation.split()

        if c == 'I':
            d = int(d)
            heapq.heappush(max_pq, -d)
            heapq.heappush(min_pq, d)

        else:
            if d == '1':
                while max_pq:
                    max_ = -heapq.heappop(max_pq)
                    if max_cnt.get(max_, 0) > 0:
                        max_cnt[max_] -= 1
                    else:
                        min_cnt[max_] = min_cnt.get(max_, 0) + 1
                        break
            else:
                while min_pq:
                    min_ = heapq.heappop(min_pq)
                    if min_cnt.get(min_, 0) > 0:
                        min_cnt[min_] -= 1
                    else:
                        max_cnt[min_] = max_cnt.get(min_, 0) + 1
                        break

    while max_pq:
        max_ = -heapq.heappop(max_pq)
        if max_cnt.get(max_, 0) > 0:
            max_cnt[max_] -= 1
        else:
            answer[0] = max_
            break

    while min_pq:
        min_ = heapq.heappop(min_pq)
        if min_cnt.get(min_, 0) > 0:
            min_cnt[min_] -= 1
        else:
            answer[1] = min_
            break

    return answer