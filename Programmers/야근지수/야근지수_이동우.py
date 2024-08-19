def solution(n, works):
    works.sort()
    l = len(works)
    i = l - 1
    while n:
        while i > 0 and works[i] == works[i - 1]:
            i -= 1
        for j in range(l - 1, i - 1, -1):
            works[j] -= 1
            n -= 1
            if not n:
                break

    answer = 0
    for w in works:
        if w > 0:
            answer += w ** 2
    return answer