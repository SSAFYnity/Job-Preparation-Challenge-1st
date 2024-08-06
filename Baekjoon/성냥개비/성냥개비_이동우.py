import sys
input = sys.stdin.readline

T = int(input())
for i in range(T):
    n = int(input())

    z_cnt = (n - 1) // 7
    Sn = '1' + '0' * z_cnt
    if z_cnt * 6 <= n - 2 <= z_cnt * 7:
        e_cnt = n - 2 - z_cnt * 6
        if e_cnt:
            Sn = Sn[:-e_cnt] + '8' * e_cnt
    elif z_cnt * 6 <= n - 5 <= z_cnt * 7:
        e_cnt = n - 5 - z_cnt * 6
        Sn = '2' + Sn[1:-e_cnt] + '8' * e_cnt
    elif z_cnt * 6 <= n - 6 <= z_cnt * 7:
        e_cnt = n - 6 - z_cnt * 6
        Sn = '6' + Sn[1:-e_cnt] + '8' * e_cnt
    elif z_cnt * 6 <= n - 7 <= z_cnt * 7:
        e_cnt = n - 7 - z_cnt * 6
        Sn = '8' + Sn[1:-e_cnt] + '8' * e_cnt
    if n == 2:
        Sn = 1
    if n == 3:
        Sn = 7
    if n == 4:
        Sn = 4
    if n == 10:
        Sn = 22
    if n == 11:
        Sn = 20
    if n == 17:
        Sn = 200

    Ln = '1' * (n // 2)
    if n % 2:
        Ln = '7' + Ln[1:]

    print(Sn, Ln)