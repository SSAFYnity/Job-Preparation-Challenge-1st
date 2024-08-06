N, S = map(int, input().split())

arr = list(map(int, input().split()))


min_length= 1000000

spoint = 0
epoint = 0
sum = 0
while True:


    if sum >= S:
        min_length=min(min_length,epoint-spoint)
        sum -= arr[spoint]
        # print(sum)
        # print(min_length)
        spoint += 1
    elif epoint == N:
        break;
    else:
        sum += arr[epoint]
        epoint += 1

if min_length == 1000000:
    print(0)
else:
    print(min_length)
