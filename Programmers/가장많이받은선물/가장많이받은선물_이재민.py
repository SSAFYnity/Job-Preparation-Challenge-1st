def solution(friends, gifts):
    friends_idx = dict()
    cnt = 0
    for friend in friends:
        friends_idx[friend] = cnt
        cnt += 1
    total = [[0] * (cnt + 1) for _ in range(cnt)]

    for gift in gifts:
        A, B = gift.split()
        A_idx = friends_idx[A]
        B_idx = friends_idx[B]
        total[A_idx][B_idx] += 1
        total[B_idx][A_idx] -= 1

    for idx in range(cnt):
        total[idx][cnt] = sum(total[idx])

    ans = 0
    for A_idx in range(cnt):
        temp = 0
        for B_idx in range(cnt):
            if A_idx == B_idx: continue
            if total[A_idx][B_idx] < 0:
                continue
            elif total[A_idx][B_idx] > 0:
                temp += 1
            elif total[A_idx][cnt] > total[B_idx][cnt]:
                temp += 1
        ans = max(ans, temp)
    return ans