N = int(input())
S = input()
half_dict = dict()
for i in range(N // 2):
    if S[i] in half_dict.keys():
        half_dict[S[i]] += 1
    else:
        half_dict[S[i]] = 1
for i in range(N - N // 2, N):
    if S[i] in half_dict.keys():
        half_dict[S[i]] += 1
    else:
        half_dict[S[i]] = 1
for n in half_dict.values():
    if n % 2:
        print('No')
        break
else:
    print('Yes')