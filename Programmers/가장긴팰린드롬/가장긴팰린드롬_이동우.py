def solution(s):
    answer = 0
    for i in range(len(s)):
        l = i
        r = i
        ans = -1
        while l >= 0 and r < len(s):
            if s[l] == s[r]:
                ans += 2
                l -= 1
                r += 1
            else:
                break
        if ans > answer:
            answer = ans
    for i in range(len(s) - 1):
        l = i
        r = i + 1
        ans = 0
        while l >= 0 and r < len(s):
            if s[l] == s[r]:
                ans += 2
                l -= 1
                r += 1
            else:
                break
        if ans > answer:
            answer = ans
    return answer