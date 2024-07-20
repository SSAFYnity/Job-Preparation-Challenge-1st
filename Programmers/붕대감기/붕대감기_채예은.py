def solution(bandage, health, attacks):
    t, x, y = bandage
    pre, answer = 0, health
    for now, attack in attacks:
        # 현재 체력 계산
        heal = now-pre-1
        answer += heal*x + (heal//t)*y
        answer = health if answer > health else answer
        # attck 빼기
        answer -= attack
        if answer <= 0:
            answer = -1
            break
        pre = now
    
    return answer
