import math

def solution(n, k):

    answer = 0

    def base(n, k):
        if k == 10:
            return list(reversed(list(str(n))))
        res = []
        while n >= k:
            res += str(n % k)
            n //= k
        res.append(str(n))
        return res

    def is_prime(n):
        for i in range(2, int(math.sqrt(n)) + 1):
            if n % i == 0:
                return False
        return True

    num = base(n, k)

    tmp = ''
    while num:
        n = str(num.pop())
        if (not tmp and n != '0') or n != '0':
            tmp += n
        elif tmp and n == '0':
            if tmp != '1' and is_prime(int(tmp)):
                answer += 1
            tmp = ''
    if tmp and tmp != '1' and is_prime(int(tmp)):
        answer += 1

    return answer
