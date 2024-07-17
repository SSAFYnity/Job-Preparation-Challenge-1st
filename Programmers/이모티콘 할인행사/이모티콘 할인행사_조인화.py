def solution(users, emoticons):

    def calculate(len_emoticons):
        join_plus = 0
        total_price = 0
        for r, p in users:
            # 할인율이 r 이상인 이모티콘을 구매한다.
            # 총 구매액이 p 이상이면 이모티콘 플러스에 가입한다.
            price_sum = 0
            for i in range(len_emoticons):
                if r <= ratios[i]:  # 구매하는 경우
                    price_sum += emoticons[i] * (1 - ratios[i]/100)

            if price_sum >= p:  # 이모티콘 플러스에 가입하는 경우
                join_plus += 1
            else:               # 이모티콘 플러스에 가입하지 않는 경우
                total_price += price_sum

        if answer[0] < join_plus:
            answer[0] = join_plus
            answer[1] = total_price
        elif answer[0] == join_plus and answer[1] < total_price:
            answer[1] = total_price


    def perm(depth, len_emoticons):
        if depth == len_emoticons:
            calculate(len_emoticons)
            return
        for ratio in RATIO_CANDIDATES:
            ratios.append(ratio)
            perm(depth+1, len_emoticons)
            ratios.pop()


    RATIO_CANDIDATES = [10, 20, 30, 40]
    ratios = []
    answer = [0, 0]

    perm(0, len(emoticons))

    return answer