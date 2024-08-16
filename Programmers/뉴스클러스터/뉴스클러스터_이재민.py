def solution(str1, str2):
    def count(words):
        total = dict()
        for i in range(len(words)-1):
            value = words[i:i+2].lower()
            if not value.isalpha(): continue
            if value in total:total[value] += 1
            else:total[value] = 1
        return total

    v = 65536
    dict1 = count(str1)
    dict2 = count(str2)
    union_cnt,inter_cnt = 0,0
    for key,value in dict1.items():
        if key in dict2:
            union_cnt += max(value,dict2[key])
            inter_cnt += min(value,dict2[key])
        else:
            union_cnt += value
    for key,value in dict2.items():
        if key in dict1: continue
        union_cnt += value
    if union_cnt:
        return int(inter_cnt/union_cnt*v)
    else: return v