def solution(n, m, x, y, queries):
    points = [x, x + 1, y, y + 1]
    for command, dx in queries[::-1]:
        if points[0] == points[1] or points[2] == points[3]:
            break
        if command == 0:
            if points[2] == 0:
                points[3] = min(m, points[3] + dx)
            else:
                points[2] = min(m, points[2] + dx)
                points[3] = min(m, points[3] + dx)
        elif command == 1:
            if points[3] == m:
                points[2] = max(0, points[2] - dx)
            else:
                points[2] = max(0, points[2] - dx)
                points[3] = max(0, points[3] - dx)
        elif command == 2:
            if points[0] == 0:
                points[1] = min(n, points[1] + dx)
            else:
                points[0] = min(n, points[0] + dx)
                points[1] = min(n, points[1] + dx)
        elif command == 3:
            if points[1] == n:
                points[0] = max(0, points[0] - dx)
            else:
                points[0] = max(0, points[0] - dx)
                points[1] = max(0, points[1] - dx)
    return (points[1] - points[0]) * (points[3] - points[2])