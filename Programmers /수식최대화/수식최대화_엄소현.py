import itertools
            
def parsing_expression(expression):
    num = ""
    parsed_expression = []
    
    for i in range(len(expression)):
        if expression[i] in ["*", "-", "+"]:
            parsed_expression.append(int(num))
            parsed_expression.append(expression[i])
            num = ""
        else:
            num += expression[i]
            
    parsed_expression.append(int(num))
    return parsed_expression


def calc(op, expression):  
    i = 0
    while i < len(expression)-1:
        if expression[i] == op:
            if op == "*":
                expression[i] = expression[i-1] * expression[i+1]
            elif op == "+":
                expression[i] = expression[i-1] + expression[i+1]
            else:
                expression[i] = expression[i-1] - expression[i+1]

            del expression[i+1]
            del expression[i-1]
            
        else:
            i += 1
    return expression
    
    
def solution(expression):
    case = list(itertools.permutations(["*", "-", "+"], 3))
    expression = parsing_expression(expression)
    max_num = 0

    for operator in case:
        parsed_expression = expression[:]
        for op in operator:
            parsed_expression = calc(op, parsed_expression)
    
        max_num = max(max_num, abs(parsed_expression[0]))
    
    return max_num
