from collections import deque
def count_god_bless(n:int,carrier:list[tuple[int]],wall_data:list[list[set]])->list[list[int]]:
    dr,dc = (0,-1,0,1),(-1,0,1,0)
    total = [[0]*n for _ in range(n)]
    for origin_r,origin_c,d in carrier:
        if d in wall_data[origin_r][origin_c]:
            continue
        nr,nc = origin_r+dr[d], origin_c+dc[d]
        arr = [[0]*n for _ in range(n)]
        arr[nr][nc] = 5
        que = deque()
        que.append((nr,nc,5))
        if d % 2 == 0: # 좌우로 방향
            while que:
                r,c,cnt = que.popleft()
                if cnt == 0: break
                nc = c + dc[d]
                if not (0<=nc<n):continue
                ncnt = cnt - 1
                # 바로 옆
                nr = r
                if (arr[nr][nc] == 0) and not (d in wall_data[r][c]):
                    arr[nr][nc] = ncnt
                    que.append((nr,nc,ncnt))
                # 위
                nr = r-1
                if (0<=nr) and (arr[nr][nc] == 0) and not (d in wall_data[nr][c]) and not (1 in wall_data[r][c]):
                    arr[nr][nc] = ncnt
                    que.append((nr,nc,ncnt))
                # 아래
                nr = r + 1
                if (nr<n) and (arr[nr][nc] == 0) and not (d in wall_data[nr][c]) and not (3 in wall_data[r][c]):
                    arr[nr][nc] = ncnt
                    que.append((nr,nc,ncnt))
        else:   # 상하 방향
            while que:
                r,c,cnt = que.popleft()
                if cnt == 0: break
                nr = r + dr[d]
                if not (0<=nr<n): continue
                ncnt = cnt - 1
                # 바로 위아래
                nc = c
                if (arr[nr][nc]==0) and not (d in wall_data[r][c]):
                    arr[nr][nc] = ncnt
                    que.append((nr,nc,ncnt))
                # 왼쪽
                nc = c-1
                if (0<=nc) and (arr[nr][nc]==0) and not (d in wall_data[r][nc]) and not (0 in wall_data[r][c]):
                    arr[nr][nc] = ncnt
                    que.append((nr,nc,ncnt))
                # 오른쪽
                nc = c+1
                if (nc<n) and (arr[nr][nc] == 0 ) and not (d in wall_data[r][nc]) and not (2 in wall_data[r][c]):
                    arr[nr][nc] = ncnt
                    que.append((nr,nc,ncnt))
        for r in range(n):
            for c in range(n):
                total[r][c] += arr[r][c]
    return total

def spread_result(n:int,arr:list[list[int]],wall_data:list[list[set]])->list[list[int]]:
    dr, dc = (0, -1, 0, 1), (-1, 0, 1, 0)
    result = [[0]*n for _ in range(n)]
    for r in range(n):
        for c in range(n):
            for d in range(4):
                if d in wall_data[r][c]: continue
                nr,nc = r+dr[d], c+dc[d]
                if not (0<=nr<n and 0<=nc<n): continue
                if arr[nr][nc] > arr[r][c]:
                    result[r][c] += (arr[nr][nc]-arr[r][c])//4
                else:
                    result[r][c] -= (arr[r][c]-arr[nr][nc])//4
    return result

def office_check(k:int,office:list[tuple[int]],data:list[list[int]])->bool:
    for r,c in office:
        if data[r][c] < k:return False
    return True

# 왼쪽, 위, 오른쪽, 아래
n,m,k = map(int,input().split())
office = list()
carrier_is_god = list()
for r in range(n):
    room_data = list(map(int,input().split()))
    for c in range(n):
        match room_data[c]:
            case 0: continue
            case 1: office.append((r,c))
            case _: carrier_is_god.append((r,c,room_data[c]-2))
wall_data = [[set() for __ in range(n)] for _ in range(n)]
for _ in range(m):
    x,y,s = map(int,input().split())
    if s == 0:
        wall_data[x-1][y-1].add(1)
        wall_data[x-2][y-1].add(3)
    else:
        wall_data[x-1][y-1].add(0)
        wall_data[x-1][y-2].add(2)
god_bless = count_god_bless(n,carrier_is_god,wall_data)
temperature_data = [[0]*n for _ in range(n)]

ans = -1
for cnt in range(1,101):
    # step1
    for r in range(n):
        for c in range(n):
            temperature_data[r][c] += god_bless[r][c]
    # step2
    ls = spread_result(n,temperature_data,wall_data)
    for r in range(n):
        for c in range(n):
            temperature_data[r][c] += ls[r][c]
    # step3
    temperature_data[0][0] += 1
    temperature_data[0][n-1] += 1
    temperature_data[n-1][0] += 1
    temperature_data[n-1][n-1] += 1
    for i in range(n):
        temperature_data[i][0] = max(0,temperature_data[i][0]-1)
        temperature_data[0][i] = max(0,temperature_data[0][i]-1)
        temperature_data[n-1][i] = max(0,temperature_data[n-1][i]-1)
        temperature_data[i][n-1] = max(0,temperature_data[i][n-1]-1)
    # 사무실 체크
    flag = office_check(k,office,temperature_data)
    if flag:
        ans = cnt
        break
print(ans)