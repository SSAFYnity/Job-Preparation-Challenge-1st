def solution(bandage, health, attacks):
    t, x, y = bandage
    cur_health = health
    max_time = attacks[-1][0]
    attack_dict = {}
    for attack in attacks:
        attack_dict[attack[0]] = attack[1]
    
    charging = 0
    answer = 0
    
    for i in range(1, max_time + 1):
        if i in attack_dict:
            cur_health -= attack_dict[i]
            charging = 0
            if cur_health <= 0:
                answer = -1
                break
        else:
            charging += 1
            if charging < t:
                cur_health = min(health, cur_health + x)
            elif charging == t:
                cur_health = min(health, cur_health + x + y)
                charging = 0
                
    if answer != -1:
        answer = cur_health
        
    return answer