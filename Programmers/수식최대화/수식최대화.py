def permutation(op, ops, i=0):
    if i == len(op):
        ops.append(op.copy())
    else:
        for j in range(i, len(op)):
            op[i], op[j] = op[j], op[i]
            permutation(op, ops, i + 1)
            op[i], op[j] = op[j], op[i]
    return ops

def calculation(arr, op):
    stack = [arr.pop()]
    while op:
        o = op.pop()
        while arr:
            temp = arr.pop()
            if temp == o:
                if o == '+':
                    stack.append(stack.pop() + arr.pop())
                elif o == '-':
                    stack.append(stack.pop() - arr.pop())
                else:
                    stack.append(stack.pop() * arr.pop())
            else:
                stack.append(temp)
        stack.reverse()
        arr = stack
        stack = [arr.pop()]
    return stack[0]

def solution(expression):

    answer = 0

    arr, op = list(), set()
    temp = ""

    for letter in expression:
        if letter.isnumeric():
            temp += letter
        else:
            arr.append(int(temp))
            temp = ""
            arr.append(letter)
            op.add(letter)
    arr.append(int(temp))
    arr.reverse()

    op = list(op)
    ops = list()
    permutation(op, ops)

    while ops:
        op = ops.pop()
        arr1 = arr.copy()
        answer = max(answer, abs(calculation(arr1, op)))

    return answer


expression = "50*6-3*2"
print(solution(expression))
