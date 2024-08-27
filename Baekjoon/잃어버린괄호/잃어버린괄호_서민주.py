a = input()
b = a.split("-")
result = []

for i in b:
    if "+" not in i:
        result.append(int(i))
    else:
        c = i.split("+")
        ans = 0
        for k in c:
            ans += int(k)
        result.append(int(ans))

res = result[0]
for i in range(1, len(result)):
    res -= result[i]

print(res)