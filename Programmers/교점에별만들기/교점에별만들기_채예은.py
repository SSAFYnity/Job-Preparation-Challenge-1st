def solution(line):
    n = len(line)
    points = []
    min_x = min_y = float('inf')
    max_x = max_y = float('-inf')
    for i in range(n-1):
        a, b, e = line[i]
        for j in range(i+1, n):
            c, d, f = line[j]
            if a*d - b*c != 0:
                x, y = (b*f-e*d)/(a*d-b*c), (e*c-a*f)/(a*d-b*c)
                if float(x).is_integer() and float(y).is_integer():
                    x, y = int(x), int(y)
                    points.append((x, y))
                    min_x, max_x, min_y, max_y = min(min_x, x), max(max_x, x), min(min_y, y), max(max_y, y)

    answer = []
    for i in range(max_y, min_y-1, -1):
        row = ''
        for j in range(min_x, max_x+1):
            if (j, i) in points:
                row += '*'
            else:
                row += '.'
        answer.append(row)
    
    return answer
