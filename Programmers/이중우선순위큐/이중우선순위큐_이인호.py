from collections import deque


def solution(operations):
    answer = [0, 0]
    dq = deque()
    for i in range(len(operations)):
        tmp = operations[i].split(" ")
        operator = tmp[0]
        num = int(tmp[1])

        ans = list(doublequeue(dq, operator, num))

        if len(ans) == 0:
            answer = [0, 0]
        else:
            answer = [max(ans), min(ans)]
    return answer


def doublequeue(dq, operator, num):
    if operator == "I":
        if len(dq) == 0:
            dq.append(num)
        else:
            i = 0

            while i < len(dq):
                if num >= dq[i]:
                    dq.appendleft(num)
                    break;
                if i == len(dq) - 1:
                    dq.append(num)
                    break;
                i += 1

    elif operator == "D":
        if num >= 0:
            if len(dq) > 0:
                dq.popleft()
        else:
            if len(dq) > 0:
                dq.pop()
    return dq
