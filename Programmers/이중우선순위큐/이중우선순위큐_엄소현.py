from collections import deque

def solution(operations):
    q = deque()

    for operation in operations:
        op, num = operation.split()

        # 숫자 삽입
        if op == "I":
            q.append(int(num))
            q = deque(sorted(q))
        else:
            if len(q):
                if num == "1":   # 최댓값 삭제
                    q.pop()
                else:
                    q.popleft()   # 최솟값 삭제
    if len(q):
        return([q.pop(), q.popleft()])
    else:
        return[0, 0]
