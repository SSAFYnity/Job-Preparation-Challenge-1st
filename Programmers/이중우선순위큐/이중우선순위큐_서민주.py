def solution(operations):
    queue = []
    for i in operations:
        if i=='D -1':
            if queue:
                queue.remove(min(queue))
        elif i=='D 1':
            if queue:
                queue.remove(max(queue))
        else:
            a, b = i.split()
            b = int(b)
            queue.append(b)
    answer = [0, 0]
    if queue:
        answer = [max(queue), min(queue)]
    return answer