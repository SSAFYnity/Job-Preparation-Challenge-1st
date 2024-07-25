from collections import deque

def solution(operations):

    def insert(number):
        answer.add(number)
        return

    def delete(number):
        if not answer:
            return
        if number == 1:
            answer.remove(max(answer))
        elif number == -1:
            answer.remove(min(answer))
        return

    rule = {'I': insert, 'D': delete}

    answer = set()
    operations = deque(operations)
    while operations:
        function, number = map(str, operations.popleft().split())
        rule[function](int(number))

    return [max(answer), min(answer)] if answer else [0, 0]


operations = ["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]
print(solution(operations))