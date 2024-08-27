sik = input()
sik += '+'
num = ''
minus = False
ans = 0
for s in sik:
    if minus and (s == '-' or s == '+'):
        ans -= int(num)
        num = ''
    elif s == '-':
        ans += int(num)
        num = ''
        minus = True
    elif s == '+':
        ans += int(num)
        num = ''
    else:
        num += s
print(ans)