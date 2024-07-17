def code(queue):
    while True:
        for i in range(1, 6):
            # 큐의 front가 i를 마이너스 했을 때 0보다 크면 첫번째 꺼 append(pop(0) - i)
            if queue[0] - i > 0:
                queue.append(queue.pop(0) - i)
            # 0보다 작아지면 0으로 바꾸고 함수 빠져나가기!
            else:
                queue.pop(0)
                queue.append(0)
                return



for _ in range(10):
    N = int(input())
    queue = list(map(int, input().split()))
    code(queue)
    print(f'#{N}', *queue)