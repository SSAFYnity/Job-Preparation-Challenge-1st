import sys
input = sys.stdin.readline

q = int(input())
for _ in range(q):
    order = list(map(int, input().split()))
    if order[0] == 100:
        n, m, B_NUM = order[1], order[2], order[3:]
        belts = [[-1, -1, 0] for _ in range(n + 1)]
        gifts = [[-1, -1] for _ in range(m + 1)]
        for i in range(m):
            belt_n = B_NUM[i]
            gift_n = i + 1
            if belts[belt_n][0] == -1:
                belts[belt_n][0] = gift_n
            else:
                gifts[gift_n][0] = belts[belt_n][1]
                gifts[belts[belt_n][1]][1] = gift_n
            belts[belt_n][1] = gift_n
            belts[belt_n][2] += 1
    if order[0] == 200:
        m_src, m_dst = order[1], order[2]
        if belts[m_src][2]:
            if belts[m_dst][2]:
                gifts[belts[m_src][1]][1] = belts[m_dst][0]
                gifts[belts[m_dst][0]][0] = belts[m_src][1]
                belts[m_dst][2] += belts[m_src][2]
                belts[m_dst][0] = belts[m_src][0]
                belts[m_src] = [-1, -1, 0]
            else:
                belts[m_dst] = belts[m_src][:]
                belts[m_src] = [-1, -1, 0]
        print(belts[m_dst][2])
    if order[0] == 300:
        m_src, m_dst = order[1], order[2]
        front_src = belts[m_src][0]
        front_dst = belts[m_dst][0]
        if front_src != -1:
            belts[m_src][2] -= 1
            if belts[m_src][2]:
                belts[m_src][0] = gifts[front_src][1]
                gifts[gifts[front_src][1]][0] = -1
            else:
                belts[m_src] = [-1, -1, 0]
        if front_dst != -1:
            belts[m_dst][2] -= 1
            if belts[m_dst][2]:
                belts[m_dst][0] = gifts[front_dst][1]
                gifts[gifts[front_dst][1]][0] = -1
            else:
                belts[m_dst] = [-1, -1, 0]
        if front_src != -1:
            gifts[front_src][1] = belts[m_dst][0]
            belts[m_dst][2] += 1
            if belts[m_dst][2] == 1:
                belts[m_dst][1] = front_src
            else:
                gifts[belts[m_dst][0]][0] = front_src
            belts[m_dst][0] = front_src
        if front_dst != -1:
            gifts[front_dst][1] = belts[m_src][0]
            belts[m_src][2] += 1
            if belts[m_src][2] == 1:
                belts[m_src][1] = front_dst
            else:
                gifts[belts[m_src][0]][0] = front_dst
            belts[m_src][0] = front_dst
        print(belts[m_dst][2])
    if order[0] == 400:
        m_src, m_dst = order[1], order[2]
        half = belts[m_src][2] // 2
        if half:
            move_belt = [belts[m_src][0], belts[m_src][0], half]
            for _ in range(half - 1):
                move_belt[1] = gifts[move_belt[1]][1]
            belts[m_src][0] = gifts[move_belt[1]][1]
            belts[m_src][2] -= half
            gifts[belts[m_src][0]][0] = -1
            gifts[move_belt[1]][1] = belts[m_dst][0]
            if belts[m_dst][2]:
                gifts[belts[m_dst][0]][0] = move_belt[1]
            else:
                belts[m_dst][1] = move_belt[1]
            belts[m_dst][0] = move_belt[0]
            belts[m_dst][2] += move_belt[2]
        print(belts[m_dst][2])
    if order[0] == 500:
        p_num = order[1]
        a, b = gifts[p_num]
        print(a + 2 * b)
    if order[0] == 600:
        b_num = order[1]
        a, b, c = belts[b_num]
        print(a + 2 * b + 3 * c)