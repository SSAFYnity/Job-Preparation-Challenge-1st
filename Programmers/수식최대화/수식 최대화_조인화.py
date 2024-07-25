def parse(expression):
    tmp = 0
    rst = []
    for c in expression:
        if c.isdigit():
            tmp = tmp * 10 + int(c)
        else:
            rst.append(tmp)
            tmp = 0
            rst.append(c)
    return rst + [tmp]


def calculate(expression1, sequence):
    for operator in sequence:
        expression2 = []
        i = 0
        while i < len(expression1):
            if expression1[i] == operator:
                if operator == '+':
                    expression2[-1] += expression1[i+1]
                elif operator == '-':
                    expression2[-1] -= expression1[i+1]
                else:
                    expression2[-1] *= expression1[i+1]
                i += 2

            else:
                expression2.append(expression1[i])
                i += 1

        expression1 = expression2

    return abs(expression1[0])


def solution(expression):
    expression = parse(expression)

    answer = 0
    operators = ['+', '-', '*']
    for i in range(3):
        for j in range(3):
            if j == i:
                continue
            rst = calculate(expression, [operators[i], operators[j], operators[3-i-j]])
            if answer < rst:
                answer = rst

    return answer