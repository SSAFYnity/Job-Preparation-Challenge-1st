from collections import defaultdict

n, m = map(int, input().split())
dic = defaultdict(int)
for _ in range(n):
    a = input()
    if len(a)>=m:
        dic[a] += 1

dic_final = sorted(dic.items(), key=lambda x:(-x[1], -len(x[0]), x[0]))
for word in dic_final:
    print(word[0])