def solution(n, s, a, b, fares):
    
    def routes(e):
        E = [20000000] * (n+1)
        stk = [e]
        E[e] = 0
        while stk:
            now = stk.pop()
            for way in ways[now]:
                if E[now] + way[1] < E[way[0]]:
                    E[way[0]] = E[now] + way[1]
                    if way[0] not in stk:
                        stk.append(way[0])
        return E

    ways = [[] for _ in range(n+1)]
    for fare in fares:
        ways[fare[0]].append([fare[1], fare[2]])
        ways[fare[1]].append([fare[0], fare[2]])
    
    S = routes(s)
    A = routes(a)
    B = routes(b)

    answer = 60000000
    for i in range(1, n+1):
        if S[i] + A[i] + B[i] < answer:
            answer = S[i] + A[i] + B[i]
    return answer