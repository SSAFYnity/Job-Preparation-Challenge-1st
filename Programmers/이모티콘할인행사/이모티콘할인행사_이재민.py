from itertools import product
def solution(users, emoticons):
    max_value,max_pay = 0,0
    E,U = len(emoticons),len(users)
    
    total = product((10,20,30,40), repeat=E)
    for target in total:
        pay = [0]*U
        for i in range(E):
            for idx in range(U):
                user = users[idx]
                if user[0] <= target[i]:
                    pay[idx] += emoticons[i] * (100-target[i]) / 100
        temp_value = 0
        temp_pay = 0
        for idx in range(U):
            if pay[idx] >= users[idx][1]:
                temp_value += 1
            else:
                temp_pay += pay[idx]
        if temp_value > max_value:
            max_value = temp_value
            max_pay = temp_pay
        elif temp_value == max_value:
            max_pay = max(max_pay,temp_pay)

    return [max_value,max_pay]