import sys
input = sys.stdin.readline

N, M = map(int, input().split())
word_dict = dict()
for _ in range(N):
    word = input().rstrip()
    if len(word) >= M:
        if word in word_dict.keys():
            word_dict[word] += 1
        else:
            word_dict[word] = 1
word_list = []
for word, n in word_dict.items():
    word_list.append([-n, -len(word), word])
word_list.sort()
for _, _, word in word_list:
    print(word)