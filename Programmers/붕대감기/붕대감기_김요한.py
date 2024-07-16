def solution(bandage, health, attacks):
    answer = 0
    N, M = attacks[-1][0], health
    attacks_dict = dict()
    for attack in attacks:
        attacks_dict[attack[0]] = attack[1]

    cnt = bandage[0]
    for second in range(N + 1 + 1):

        if second == N + 1:
            answer = health
            break

        if second > 0:
            cnt -= 1

        if second in attacks_dict:
            cnt = bandage[0]
            health -= attacks_dict[second]
            if health <= 0:
                answer -= 1
                break
        else:
            if cnt == 0:
                cnt = bandage[0]
                if health + bandage[2] >= M:
                    health = M
                else:
                    health += bandage[2]
            if health + bandage[1] >= M:
                health = M
            else:
                health += bandage[1]

    return answer


bandage = [5, 1, 5]
health = 30
attacks = [[2, 10], [9, 15], [10, 5], [11, 5]]
print(solution(bandage, health, attacks))