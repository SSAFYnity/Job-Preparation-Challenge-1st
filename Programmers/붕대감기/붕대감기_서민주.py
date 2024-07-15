def solution(bandage, health, attacks):
    # 시전 시간 t, 초당 회복량 x, 추가 회복량 y
    t, x, y = bandage[0], bandage[1], bandage[2]
    # 현재 체력 상태 및 최대 체력 한도
    now_health, mx_health = health, health
    # 연속 성공 횟수
    success = 0
    
    flag = 0
    for i in range(1, attacks[-1][0] + 1):
        if any(i == attack[0] for attack in attacks):
            flag = 1
        else:
            flag = 0
        # 공격받은 경우
        if flag==1:
            success = 0
            matching_values = [attack[1] for attack in attacks if attack[0] == i]
            now_health -= matching_values[0]
            if now_health <=0:
                now_health = -1
                break
            
        # 공격받지 않은 경우
        elif flag==0:
            if success==t-1:
                success = 0
                now_health += (x+y)
                if now_health>mx_health:
                    now_health = mx_health
            else:
                success += 1
                now_health += x
                if now_health>mx_health:
                    now_health = mx_health
    
    if now_health<=0:
        now_health = -1
    return now_health