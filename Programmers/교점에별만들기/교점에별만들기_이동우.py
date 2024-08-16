def solution(line):
    star_x = []
    star_y = []
    star = []
    for i in range(len(line)):
        for j in range(i + 1, len(line)):
            A, B, E = line[i]
            C, D, F = line[j]
            if A * D != B * C:
                x = int((B * F - E * D) / (A * D - B * C))
                y = int((E * C - A * F) / (A * D - B * C))
                if not A * x + B * y + E and not C * x + D * y + F:
                    star_x.append(x)
                    star_y.append(y)
                    if [-y, x] not in star:
                        star.append([-y, x])
    star.sort()
    answer = []
    i = 0
    for y in range(max(star_y), min(star_y) - 1, -1):
        line = ''
        for x in range(min(star_x), max(star_x) + 1):
            if i != len(star) and x == star[i][1] and y == -star[i][0]:
                line += '*'
                i += 1
            else:
                line += '.'
        answer.append(line)
    return answer