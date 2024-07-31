ab_str = input()
a_cnt = 0
for ab in ab_str:
    if ab == 'a':
        a_cnt += 1
b_cnt = 0
for ab in ab_str[:a_cnt]:
    if ab == 'b':
        b_cnt += 1
ans = b_cnt
l = 0
r = a_cnt
ab_str += ab_str[:a_cnt]
while r < len(ab_str):
    if ab_str[l] == 'b':
        b_cnt -= 1
    if ab_str[r] == 'b':
        b_cnt += 1
    if b_cnt < ans:
        ans = b_cnt
    l += 1
    r += 1
print(ans)