def solution(commands):
    answer = []
    table = [[['', [i, j]] for j in range(51)] for i in range(51)]
    for command in commands:
        c = command.split()
        if c[0] == 'UPDATE':
            if len(c) == 4:
                rc = table[int(c[1])][int(c[2])][1]
                table[rc[0]][rc[1]][0] = c[3]
            if len(c) == 3:
                for i in range(51):
                    for j in range(51):
                        if table[i][j][0] == c[1]:
                            table[i][j][0] = c[2]
        elif c[0] == 'MERGE':
            rc = [table[int(c[1])][int(c[2])][1], table[int(c[3])][int(c[4])][1]]
            if table[rc[0][0]][rc[0][1]][0]:
                value = table[rc[0][0]][rc[0][1]][0]
            else:
                value = table[rc[1][0]][rc[1][1]][0]
            rc.sort()
            for i in range(51):
                for j in range(51):
                    if table[i][j][1] == rc[0] or table[i][j][1] == rc[1]:
                        table[i][j][0] = ''
                        table[i][j][1] = rc[0]
            table[rc[0][0]][rc[0][1]][0] = value
        elif c[0] == 'UNMERGE':
            rc = table[int(c[1])][int(c[2])][1]
            value = table[rc[0]][rc[1]][0]
            for i in range(51):
                for j in range(51):
                    if table[i][j][1] == rc:
                        table[i][j][0] = ''
                        table[i][j][1] = [i, j]
            table[int(c[1])][int(c[2])][0] = value
        else:
            rc = table[int(c[1])][int(c[2])][1]
            value = table[rc[0]][rc[1]][0]
            if not value:
                value = 'EMPTY'
            answer.append(value)
    return answer