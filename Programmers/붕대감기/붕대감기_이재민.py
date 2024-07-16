def solution(bandage, health, attacks):
    t,x,y = bandage
    hp = health
    now_time = 0
    for time,damage in attacks:
        time_gap = time - now_time - 1
        hp = min(health,hp + x*time_gap + y*(time_gap//t)) - damage
        if hp <=0: return -1
        now_time = time
        print(hp)
    return hp