# 바이러스 백신 - 양희제

import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline


def bfs(hospitals):
    global copy_cnt_virus
    queue = deque(hospitals)
    turn = 0
    while queue and copy_cnt_virus != 0:
        turn += 1
        for _ in range(len(queue)):
            h_y, h_x = queue.popleft()
            for d in directions:
                h_dy, h_dx = h_y + d[0], h_x + d[1]
                if 0 <= h_dy < N and 0 <= h_dx < N and (copy_board[h_dy][h_dx] in [0, 2]):
                    queue.append((h_dy, h_dx))
                    if copy_board[h_dy][h_dx] == 0:
                        copy_cnt_virus -= 1
                    copy_board[h_dy][h_dx] = 3
    
    return turn if copy_cnt_virus == 0 else -1

N, M = map(int, input().split())
board = []
cnt_virus = 0
hospital_list = []
directions = [(-1, 0), (0, -1), (1, 0), (0, 1)]
answer = -1

for i in range(N):
    row = list(map(int, input().split()))
    for j in range(N):
        if row[j] == 0:
            cnt_virus += 1
        elif row[j] == 2:
            hospital_list.append((i, j))
    board.append(row)

for h_case in combinations(hospital_list, M):
    copy_cnt_virus = cnt_virus
    copy_board = []
    for i in range(N):
        copy_board.append(board[i][:])
    for h_i, h_j in h_case:
        copy_board[h_i][h_j] = 3
    
    turn = bfs(h_case)
    if answer == -1:
        answer = turn
    elif turn != -1:
        answer = min(answer, turn)

print(answer)