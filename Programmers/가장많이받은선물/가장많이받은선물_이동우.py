def solution(friends, gifts):
    keys = {}
    for i in range(len(friends)):
        keys[friends[i]] = i
    point = [0] * len(friends)
    table = [[0] * len(friends) for _ in range(len(friends))]
    for gift in gifts:
        a, b = gift.split()
        table[keys[a]][keys[b]] += 1
        point[keys[a]] += 1
        point[keys[b]] -= 1
    present = [0] * len(friends)
    for a in range(len(friends)):
        for b in range(a+1, len(friends)):
            if table[a][b] - table[b][a] > 0:
                present[a] += 1
            elif table[a][b] - table[b][a] < 0:
                present[b] += 1
            elif point[a] - point[b] > 0:
                present[a] += 1
            elif point[a] - point[b] < 0:
                present[b] += 1
    return max(present)