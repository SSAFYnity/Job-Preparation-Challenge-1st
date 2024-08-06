import sys
input = sys.stdin.readline

N = int(input())
connect = [[] for _ in range(N + 1)]
for _ in range(N - 1):
    a, b = map(int, input().split())
    connect[a].append(b)
    connect[b].append(a)
logn = 0
while 2 ** logn < N:
    logn += 1
node_info = [[0] * (logn + 1) for _ in range(N + 1)]
node_info[1][0] = 1
stack = [1]
while stack:
    node = stack.pop()
    for n in connect[node]:
        if not node_info[n][0]:
            node_info[n][0] = node_info[node][0] + 1
            node_info[n][1] = node
            for i in range(2, logn + 1):
                nn = node_info[n][i - 1]
                if not node_info[nn][i - 1]:
                    break
                node_info[n][i] = node_info[nn][i - 1]
            stack.append(n)

M = int(input())
for _ in range(M):
    a, b = map(int, input().split())
    if node_info[a][0] > node_info[b][0]:
        l = node_info[a][0] - node_info[b][0]
        i = 1
        while bin(l)[-i] != 'b':
            if bin(l)[-i] == '1':
                a = node_info[a][i]
            i += 1
    if node_info[a][0] < node_info[b][0]:
        l = node_info[b][0] - node_info[a][0]
        i = 1
        while bin(l)[-i] != 'b':
            if bin(l)[-i] == '1':
                b = node_info[b][i]
            i += 1
    while a != b:
        for i in range(2, logn + 1):
            if node_info[a][i] == node_info[b][i]:
                a = node_info[a][i - 1]
                b = node_info[b][i - 1]
                break
    print(a)