
n = int(input())

pal = input()
tmp =[]
dict = {}
if len(pal)%2 ==0:
    k = n // 2
    palOne = pal[:k]
    palTwo = pal[k:]
else:
    k = n//2+1
    palOne = pal[:k-1]
    palTwo = pal[k:]

for i in palTwo:
    tmp.append(i)
palTwo = tmp
for i in palOne:
    if dict.get(i) != None:
        dict[i] = dict[i]+1
    else:
        dict[i] =1
for i in palTwo:
    if dict.get(i) != None:
        dict[i] = dict[i]+1
    else:
        dict[i] =1

answer= True

for i in dict:

    if dict[i]%2 != 0:
        answer= False
if answer:
    print("Yes")
else:
    print('No')
