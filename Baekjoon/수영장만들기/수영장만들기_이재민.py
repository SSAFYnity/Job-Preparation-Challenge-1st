def solution(R:int,C:int,arr:list[list[int]])->int:
    dr,dc = (0,0,-1,1),(1,-1,0,0)
    total = 0
    for h in range(1,10):
        temp = [[0]*C for _ in range(R)]
        cnt = 0
        for r in range(R):
            for c in range(C):
                if arr[r][c]<h:
                    temp[r][c] = 1
                    cnt += 1
        ls = []
        # 밖으로 흐르는 것 확인
        for r in range(R):
            if temp[r][0] == 1:
                ls.append((r,0))
                temp[r][0] = 2
            if temp[r][C-1] == 1:
                ls.append((r,C-1))
                temp[r][C-1] = 2
        for c in range(C):
            if temp[0][c] == 1:
                ls.append((0,c))
                temp[0][c] = 2
            if temp[R-1][c] == 1:
                ls.append((R-1,c))
                temp[R-1][c] = 2
        while ls:
            cnt -= 1
            r,c = ls.pop()
            for d in range(4):
                nr,nc = r+dr[d], c+dc[d]
                if not (0<=nr<R and 0<=nc<C) or temp[nr][nc] != 1:continue
                temp[nr][nc] = 2
                ls.append((nr,nc))
        total += cnt
    return total

N,M = map(int,input().split())
arr = [list(map(int,input())) for _ in range(N)]
ans = solution(N,M,arr)
print(ans)