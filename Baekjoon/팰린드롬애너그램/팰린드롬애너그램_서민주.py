len_s = int(input())
s = input()

cnt = [0] * 26

for char in s:
    cnt[ord(char) - ord('a')] += 1

if len_s % 2 == 1:
    cnt[ord(s[len_s // 2]) - ord('a')] -= 1  # 중간 문자는 빼주기

flag = 0
for count in cnt:
    if count % 2 == 1:
        flag = 1

if flag==1:
    print("No")
else:
    print("Yes")