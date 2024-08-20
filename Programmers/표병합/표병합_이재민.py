def solution(commands):
    max_num = 5051
    group = [set([i]) for i in range(max_num)]  # 그룹 체크
    arr = [[set() for __ in range(51) ]for _ in range(51)]  # 값 체크
    ans = list()
    for command in commands:
        cmd, *detail = command.split()
        if cmd == "UPDATE":
            if len(detail) == 3:
                r,c = int(detail[0]),int(detail[1])
                value = detail[2]
                arr[r][c].clear()
                arr[r][c].add(value)
            elif len(detail) == 2:
                value1,value2 = detail
                for r in range(51):
                    for c in range(51):
                        if value1 in arr[r][c]:
                            arr[r][c].clear()
                            arr[r][c].add(value2)
        elif cmd == "MERGE":
            r1,c1,r2,c2 = map(int,detail)
            if r1 == r2 and c1 == c2: continue
            num1 = r1*100+c1
            num2 = r2*100+c2
            group[num1].update(group[num2])
            group[num2].update(group[num1])
            if arr[r1][c1] or not arr[r2][c2]:
                group[num1].update(group[num2])
                for num in group[num2]:
                    r,c = divmod(num,100)
                    arr[r][c] = arr[r1][c1]
                    group[num] = group[num1]
            else:
                group[num2].update((group[num1]))
                for num in group[num1]:
                    r,c = divmod(num,100)
                    arr[r][c] = arr[r2][c2]
                    group[num] = group[num2]
        elif cmd == "UNMERGE":
            cmd_r,cmd_c = map(int,detail)
            value = arr[cmd_r][cmd_c]
            cmd_num = 100*cmd_r + cmd_c
            temp = [num for num in group[cmd_num]]
            for num in temp:
                group[num] = set([num])
                r,c = divmod(num,100)
                arr[r][c] = set()
            arr[cmd_r][cmd_c] = value
        elif cmd == "PRINT":
            r,c = map(int,detail)
            if arr[r][c]:ans.append(*arr[r][c])
            else: ans.append("EMPTY")
    return ans