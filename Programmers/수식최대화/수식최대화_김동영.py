def calculate(expression, operators):
    numbers = []
    ops = []
    num = ""
    for char in expression:
        if char.isdigit():
            num += char
        else:
            numbers.append(int(num))
            ops.append(char)
            num = ""
    numbers.append(int(num))

    for operator in operators:
        while operator in ops:
            idx = ops.index(operator)
            if operator == '+':
                result = numbers[idx] + numbers[idx + 1]
            elif operator == '-':
                result = numbers[idx] - numbers[idx + 1]
            elif operator == '*':
                result = numbers[idx] * numbers[idx + 1]
            
            numbers[idx] = result
            
            del numbers[idx + 1]
            del ops[idx]

    return abs(numbers[0])

def solution(expression):
    answer = 0
    operators = ["+", "-", "*"]
    comb = []

    def dfs(stack):
        if len(stack) == 3:
            comb.append(stack[:])
            return
        
        for i in range(3):
            if operators[i] not in stack:
                stack.append(operators[i])
                dfs(stack)
                stack.pop()
    
    dfs([])

    for c in comb:
        result = calculate(expression, c)
        answer = max(answer, result)
    
    return answer