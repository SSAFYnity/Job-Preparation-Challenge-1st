def interaction(i, r, c):
    if r <= 0 or r > N or c <= 0 or c > N:
        S[i - 1][3] = True
        return
    for Si, Sr, Sc, _, _, _ in S:
        if i == Si:
            continue
        if Sr == r and Sc == c:
            S[Si - 1][1] += move[0]
            S[Si - 1][2] += move[1]
            interaction(Si, Sr + move[0], Sc + move[1])

N, M, P, C, D = map(int, input().split())
R = list(map(int, input().split()))
# 산타의 정보 [번호, r, c, 탈락 여부, 기절 횟수, 점수]
S = []
for _ in range(P):
    S.append(list(map(int, input().split())) + [False, 0, 0])
S.sort()

turn = 0
while turn < M:
    turn += 1

    # 루돌프의 움직임

    # 가장 가까운 산타 선택
    CS = [0, -5000, 0, 0]
    for i, r, c, fail, _, _ in S:
        if fail:
            continue
        far = (R[0] - r) ** 2 + (R[1] - c) ** 2
        if [-far, r, c] > CS[1:]:
            CS = [i, -far, r, c]
    if not CS[0]:
        break
    # 이동
    move = [0, 0]
    if CS[2] > R[0]:
        move[0] = 1
    if CS[2] < R[0]:
        move[0] = -1
    if CS[3] > R[1]:
        move[1] = 1
    if CS[3] < R[1]:
        move[1] = -1
    R[0] += move[0]
    R[1] += move[1]
    # 충돌 및 상호 작용
    for i, r, c, _, _, _ in S:
        if R == [r, c]:
            S[i - 1][5] += C
            S[i - 1][1] += move[0] * C
            S[i - 1][2] += move[1] * C
            S[i - 1][4] = 2
            r = S[i - 1][1]
            c = S[i - 1][2]
            if r <= 0 or r > N or c <= 0 or c > N:
                S[i - 1][3] = True
            else:
                interaction(i, r, c)

    # 산타의 움직임

    for i, r, c, fail, fall, _ in S:
        if fail or fall:
            continue
        # 이동 방향 찾기
        ways = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        Sm = [0, 0]
        for way in ways:
            for _, Sr, Sc, _, _, _ in S:
                if Sr == r + way[0] and Sc == c + way[1]:
                    break
            else:
                if (R[0] - (r + way[0])) ** 2 + (R[1] - (c + way[1])) ** 2 < (R[0] - (r + Sm[0])) ** 2 + (R[1] - (c + Sm[1])) ** 2:
                    Sm = way
        # 이동 및 충돌 및 상호 작용
        S[i - 1][1] = r + Sm[0]
        S[i - 1][2] = c + Sm[1]
        if R[0] == r + Sm[0] and R[1] == c + Sm[1]:
            move = [-Sm[0], -Sm[1]]
            S[i - 1][5] += D
            S[i - 1][1] += move[0] * D
            S[i - 1][2] += move[1] * D
            S[i - 1][4] = 2
            r = S[i - 1][1]
            c = S[i - 1][2]
            if r <= 0 or r > N or c <= 0 or c > N:
                S[i - 1][3] = True
            else:
                interaction(i, r, c)
    # 살아남았다는건 점수를 얻는다는것
    for i, _, _, fail, fall, _ in S:
        if not fail:
            S[i - 1][5] += 1
        if fall:
            S[i - 1][4] -= 1

answer = []
for _, _, _, _, _, ans in S:
    answer.append(ans)
print(*answer)