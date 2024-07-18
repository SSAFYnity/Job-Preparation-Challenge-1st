def solution(users, emoticons):
    answer = [0, 0]
    disPercent = [10, 20, 30, 40]
    discount = []
    maxSum = 0
    maxPlus = 0

    def dfs(tmp, depth):
        if len(tmp) == depth:
            discount.append(tmp[:])
            return
        for i in disPercent:
            tmp[depth] += i
            dfs(tmp, depth + 1)
            tmp[depth] -= i

    dfs([0] * len(emoticons), 0)

    # 완전탐색
    for d in range(len(discount)):
        plus, price = 0, [0] * len(users)
        for e in range(len(emoticons)):
            for u in range(len(users)):
                if users[u][0] <= discount[d][e]:
                    price[u] += emoticons[e] * (100 - discount[d][e]) / 100

        for u in range(len(users)):
            if price[u] >= users[u][1]:
                plus += 1
                price[u] = 0

        if plus >= maxPlus:
            if plus == maxPlus:
                maxSum = max(maxSum, sum(price))
            else:
                maxSum = sum(price)
            maxPlus = plus
            answer = [plus, maxSum]

    return answer

