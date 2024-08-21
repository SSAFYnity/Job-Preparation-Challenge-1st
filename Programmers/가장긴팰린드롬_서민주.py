def solution(s):
    answer = 1
    n = len(s)
    
    for i in range(n):
        for j in range(i + 1, n + 1):
            substring = s[i:j]
            if substring == substring[::-1]:
                answer = max(answer, j - i)

    return answer