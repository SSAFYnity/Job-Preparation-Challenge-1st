def solution(h1, m1, s1, h2, m2, s2):
    start = h1 * 60 * 60 + m1 * 60 + s1
    end = h2 * 60 * 60 + m2 * 60 + s2
    cnt = 0

    if start == 0 or start == 12 * 3600:
        cnt += 1

    while start < end:

        # 시분초의 각도
        h_angel = start / 120 % 360
        m_angel = start / 10 % 360
        s_angel = start * 6 % 360

        if (start + 1) / 120 % 360 == 0:
            h = 360
        else:
            h = (start + 1) / 120 % 360
        if (start + 1) / 10 % 360 == 0:
            m = 360
        else:
            m = (start + 1) / 10 % 360
        if (start + 1) * 6 % 360 == 0:
            s = 360
        else:
            s = (start + 1) * 6 % 360

        if s_angel < h_angel and h <= s:
            cnt += 1
        if s_angel < m_angel and m <= s:
            cnt += 1

        # 시분초가 모두 겹칠때는 1개만 카운트
        if s == m and s == h:
            cnt -= 1
        start += 1
    return cnt