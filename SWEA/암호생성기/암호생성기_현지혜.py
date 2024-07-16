T = 10 
for tc in range(1,T+1):
    tc = int(input())
    lst = list(map(int, input().split()))
    i = 1
    while True:

        tmp = lst.pop(0)-i # 첫번째 요소 빼서 -1하기
        if tmp < 0:
            tmp = 0
        lst.append(tmp)

        if tmp <=0:
            break
        i += 1

        if i > 5: # 5를 넘으면 숫자 갱신
            i = 1


    print('#{} '.format(tc), end='')
    print(*lst)