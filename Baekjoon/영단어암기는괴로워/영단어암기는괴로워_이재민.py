import sys
input = sys.stdin.readline
N,M = map(int,input().split())
note = list()
cnt = dict()
for _ in range(N):
    word = input().rstrip()
    if len(word) >= M:
        if word in cnt:
            cnt[word] += 1
        else:
            note.append(word)
            cnt[word] = 1
note.sort(key=lambda word:(-cnt[word],-len(word),word))
print(*note,sep='\n')