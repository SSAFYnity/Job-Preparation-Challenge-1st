def solution(bandage, health, attacks):
    current = 0
    conti = 0
    t, x, y = bandage
    at_idx = 0
    hp = health
    
    while current <= attacks[-1][0]:
        if current == attacks[at_idx][0]:
            hp -= attacks[at_idx][1]
            if hp <= 0:
                return -1
            conti = 0
            at_idx += 1
        else:
            hp = min((hp + x), health)
            conti += 1
            if conti == t:
                conti = 0
                hp = min((hp + y), health)
        current += 1
        
    return hp