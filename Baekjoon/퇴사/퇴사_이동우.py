N = int(input())
earn = [0] * (N + 6)
for i in range(1, N + 1):
    T, P = map(int, input().split())
    if earn[i + T - 1] < max(earn[:i]) + P:
        earn[i + T - 1] = max(earn[:i]) + P
print(max(earn[:N + 1]))