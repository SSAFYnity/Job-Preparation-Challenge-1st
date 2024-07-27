def solution(expression):
    words = []
    operators = []
    primarys = [["*", "+", "-"], ["*", "-", "+"], ["+", "*", "-"], ["+", "-", "*"], ["-", "+", "*"], ["-", "*", "+"]]
    num = ""
    maxnum = 0

    # expression 수식 변환
    for i in range(len(expression)):

        if expression[i] == "*" or expression[i] == "+" or expression[i] == "-":

            # 숫자는 words 배열에 연산자는 operators 배열에
            words.append(num)
            operators.append(expression[i])
            num = ""
        elif i == (len(expression) - 1):
            num += expression[i]
            words.append(num)
        else:
            num += expression[i]

    # 순서 연산
    for primary in primarys:

        # 임시배열 복제
        tmp_words = words[:]
        tmp_operators = operators[:]
        for pri in primary:
            i = 0

            # 배열 길이 변화때문에 for가 아닌 while 사용
            while i < len(tmp_operators):

                if pri == tmp_operators[i]:

                    firstNum = tmp_words[i]
                    lastNum = tmp_words[i + 1]
                    signal = tmp_operators[i]

                    if tmp_operators[i] == "*":
                        tmp = int(firstNum) * int(lastNum)
                    elif tmp_operators[i] == "+":
                        tmp = int(firstNum) + int(lastNum)
                    elif tmp_operators[i] == "-":
                        tmp = int(firstNum) - int(lastNum)
                    print(tmp)

                    # a+b =c 일때 [a,b]를 [c]로 변환
                    tmp_words[i:i + 2] = [str(tmp)]
                    tmp_operators.pop(i)
                else:
                    i += 1

        maxnum = max(maxnum, abs(int(tmp_words[0])))

    answer = maxnum
    return answer