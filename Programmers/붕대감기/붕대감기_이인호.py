def solution(bandage, health, attacks):
    t = bandage[0]
    x = bandage[1]
    y = bandage[2]
    count = 0
    nowhealth = health
    answer = 0

    for i in attacks:
        attackTime = i[0]
        attackDamage = i[1]
        attackCount = attackTime - count - 1  # 공격전 체력회복 분기
        count = attackTime

        nowhealth += attackCount * x  # 체력회복

        if attackCount // bandage[0] > 0:  # 추가 회복량 계산
            nowhealth += attackCount // t * y

        if nowhealth >= health:  # 최대체력보다 많을시 계산
            nowhealth = health

        nowhealth -= attackDamage  # 피해량 만큼 감소

        if nowhealth <= 0:  # 만약 체력이 0이하이면 - 1 반환
            answer = -1
            break;

        answer = nowhealth

    return answer