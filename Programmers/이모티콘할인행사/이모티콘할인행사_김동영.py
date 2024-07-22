def solution(users, emoticons):
    answer = [0, 0]
    
    percent = [10, 20, 30, 40]
    discount = []
    
    def dfs(stack):
        if len(stack) == len(emoticons):
            discount.append(stack[:])
            return
        for i in range(4):
            stack.append(percent[i])
            dfs(stack)
            stack.pop()
    
    dfs([])
    
    for discounts in discount:
        plus = 0
        profit = 0
        
        for user in users:
            temp = 0
            for j in range(len(emoticons)):
                if discounts[j] >= user[0]:
                    temp += emoticons[j] * ((100 - discounts[j]) / 100)
            if user[1] <= temp:
                plus += 1
            else:
                profit += temp 
        
        if answer[0] < plus:
            answer = [plus, int(profit)]
        elif answer[0] == plus:
            if answer[1] < profit:
                answer = [plus, int(profit)]
    
    return answer