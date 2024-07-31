def solution(target:str,ab_string:str)->int:
    l = len(ab_string)
    target_cnt = ab_string.count(target)
    if target_cnt == 0 or target_cnt == l: return 0
    value = 0
    for i in range(target_cnt):
        if ab_string[i] != target:value += 1
    minValue = value
    for i in range(l):
        if ab_string[i] != target:value-=1
        if ab_string[(i+target_cnt)%l] != target: value+=1
        minValue = min(minValue,value)
    return minValue

ab_string = input()
ans = solution("a",ab_string)
print(ans)