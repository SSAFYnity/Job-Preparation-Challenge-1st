def combination(k, arr=[10, 20, 30, 40], cur=[], res=[]):
    if k == 0:
        res.append(cur)
        return

    for i in range(4):
        combination(k - 1, arr, cur + [arr[i]], res)

    return res


def solution(users, emoticons):
    answer = [0, 0]
    N = len(emoticons)
    rates_comb = combination(k=N)

    while rates_comb:

        temp_answer = [0, 0]
        rates = rates_comb.pop()

        for user in users:
            temp_user = [0, 0]

            for num, rate in enumerate(rates):
                if rate >= user[0]:
                    temp_user[1] += emoticons[num] * (1 - rate / 100)

            if temp_user[1] >= user[1]:
                temp_user = [1, 0]

            temp_answer[0] += temp_user[0]
            temp_answer[1] += temp_user[1]

        answer = max(answer, temp_answer)

    return answer
