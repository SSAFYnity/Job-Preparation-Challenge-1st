from collections import defaultdict

def solution(edges):
    res = [0, 0, 0, 0]
    dic = defaultdict(lambda: [0, 0])

    for i, j in edges:
        dic[i][0] += 1
        dic[j][1] += 1

    for node, item in dic.items():
        give = item[0]
        take = item[1]
        if give>=2 and take==0:
            res[0] = node
        elif give>=2 and take>=2:
            res[3] += 1
        elif give==0 and take>=1:
            res[2] += 1

    x = dic[res[0]][0]
    res[1] = x - res[2] - res[3]
    return res