def solution(edges):
    answer = []
    infos = [[0, 0, [], []] for _ in range(1000001)]
    max_i = 0
    for a, b in edges:
        if max(a, b) > max_i:
            max_i = max(a, b)
        infos[a][0] += 1
        infos[b][1] += 1
        infos[a][2].append(b)
        infos[b][3].append(a)
    infos = infos[:max_i + 1]
    
    i = 0
    while True:
        i += 1
        if infos[i][1] == 0 and infos[i][0] > 1:
            answer = [i, 0, 0, 0]
            break
    for i in range(1, max_i + 1):
        if answer[0] in infos[i][3]:
            start = i
            now = i
            while True:
                if infos[now][0] == 0:
                    answer[2] += 1
                    break
                if infos[now][0] == 2:
                    answer[3] += 1
                    break
                now = infos[now][2][0]
                if now == start:
                    answer[1] += 1
                    break
    
    return answer