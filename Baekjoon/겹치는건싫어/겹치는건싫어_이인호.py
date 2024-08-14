n,k = map(int,input().split())

arr = input().split()

numList = [0]* 200000

spoint =0

epoint =1
maxlen =1
longarr=[]

longarr.append(arr[spoint])
numList[int(arr[spoint])]+=1
while epoint != n:

    numList[int(arr[epoint])] += 1
    # print(longarr)
    # print()

    if numList[int(arr[epoint])] <= k:

        longarr.append(arr[epoint])
        epoint += 1
        maxlen = max(maxlen,len(longarr))

    else:

        numList[int(arr[epoint])] = numList[int(arr[epoint])]-1
        numList[int(arr[spoint])] = numList[int(arr[spoint])]-1
        spoint+=1
        if len(longarr) !=0:
            longarr.pop(0)


print(maxlen)