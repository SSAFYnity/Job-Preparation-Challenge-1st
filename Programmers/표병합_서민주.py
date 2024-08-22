def solution(commands):
    merge = [[(i, j) for j in range(51)] for i in range(51)]
    arr = [["EMPTY"] * 51 for _ in range(51)]
    answer = []

    for command in commands:
        a = command.split()
        if a[0] == "UPDATE":
            # UPDATE r c value
            if len(a) == 4:
                r, c, value = int(a[1]), int(a[2]), a[3]
                y, x = merge[r][c]
                arr[y][x] = value
            # UPDATE value1 value2
            elif len(a) == 3:
                value1, value2 = a[1], a[2]
                for i in range(51):
                    for j in range(51):
                        # value1을 값으로 가지고 있는 모든 셀을 선택
                        if arr[i][j] == value1:
                            # 선택한 셀의 값을 value2로 바꿈
                            arr[i][j] = value2

        elif a[0] == "MERGE":
            r1, c1, r2, c2 = int(a[1]), int(a[2]), int(a[3]), int(a[4])
            y1, x1 = merge[r1][c1]
            y2, x2 = merge[r2][c2]
            if (y1, x1) == (y2, x2):
                continue
            if arr[y1][x1] == "EMPTY":
                arr[y1][x1] = arr[y2][x2]
            for i in range(51):
                for j in range(51):
                    if merge[i][j] == (y2, x2):
                        merge[i][j] = (y1, x1)

        elif a[0] == "UNMERGE":
            r, c = int(a[1]), int(a[2])
            y, x = merge[r][c]
            tmp = arr[y][x]

            for i in range(51):
                for j in range(51):
                    if merge[i][j] == (y, x):
                        merge[i][j] = (i, j)
                        arr[i][j] = "EMPTY"
            arr[r][c] = tmp

        elif a[0] == "PRINT":
            r, c = int(a[1]), int(a[2])
            y, x = merge[r][c]
            answer.append(arr[y][x])

    return answer