def solution(bandage, health, attacks):
    max_health = health
    index = 0
    band = 0
    t = 0
    
    while index < len(attacks):
        t += 1
        # 몬스터 공격
        if t == attacks[index][0]:
            health -= attacks[index][1]
            index += 1
            band = 0
            if health <= 0:
                return -1
        # 붕대 감기
        else:
            health = min(max_health, health + bandage[1])
            band += 1
            if band == bandage[0]:
                health = min(max_health, health + bandage[2])
                band = 0
    
    return health
