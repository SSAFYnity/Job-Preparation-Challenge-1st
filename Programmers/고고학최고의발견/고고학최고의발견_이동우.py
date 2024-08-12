def choose_cases(cases, i):
    if i == len(cases[0]):
        return cases
    new_cases = []
    for case in cases:
        for _ in range(4):
            new_cases.append(case[:])
            case[i] += 1
    return choose_cases(new_cases, i + 1)

def solution(clockHands):
    n = len(clockHands)
    answer = 3 * n * n
    fl_cases = [[0] * n]
    ways = [[0, 0], [0, 1], [0, -1], [-1, 0]]
    for case in choose_cases(fl_cases, 0):
        case_answer = sum(case)
        clocks = [case] + [[0] * n for _ in range(n - 1)]
        for i in range(1, n):
            for j in range(n):
                x = i - 1
                y = j
                c_n = clockHands[x][y]
                for xx, yy in ways:
                    xxx = x + xx
                    yyy = y + yy
                    if xxx < 0 or xxx >= n or yyy < 0 or yyy >= n:
                        continue
                    c_n += clocks[xxx][yyy]
                clocks[i][j] = 4 - c_n % 4
                if clocks[i][j] == 4:
                    clocks[i][j] = 0
                case_answer += clocks[i][j]
                if case_answer >= answer:
                    break
            if case_answer >= answer:
                break
        if case_answer >= answer:
            continue
        for i in range(n):
            x = n - 1
            y = i
            c_n = clockHands[x][y]
            for xx, yy in ways:
                xxx = x + xx
                yyy = y + yy
                if xxx < 0 or xxx >= n or yyy < 0 or yyy >= n:
                    continue
                c_n += clocks[xxx][yyy]
            if c_n % 4:
                break
        if c_n % 4:
            continue
        answer = case_answer
    return answer