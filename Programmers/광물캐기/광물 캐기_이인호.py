def solution(picks, minerals):
    min_tired = [1000000]
    dfs(picks, minerals, 0, min_tired)
    return min_tired[0]


def dfs(picks, minerals, d, min_tired):
    if len(minerals) == 0:
        print(d)
        min_tired[0] = min(min_tired[0], d)
        return
    if sum(picks) == 0:
        print(d)
        min_tired[0] = min(min_tired[0], d)
        return

    for i in range(len(picks)):
        if picks[i] > 0:
            picks[i] -= 1
            tmp = 0
            tmp_minerals = minerals[:]
            for idx in range(min(5, len(tmp_minerals))):
                if len(tmp_minerals) != 0:
                    tmp += dig(i + 1, tmp_minerals[idx])

            dfs(picks, tmp_minerals[5:], d + tmp, min_tired)
            picks[i] += 1


def dig(pick, mineral):
    if pick == 1:
        if mineral == "diamond":
            return 1
        elif mineral == "iron":
            return 1
        elif mineral == "stone":
            return 1
    elif pick == 2:
        if mineral == "diamond":
            return 5
        elif mineral == "iron":
            return 1
        elif mineral == "stone":
            return 1
    elif pick == 3:
        if mineral == "diamond":
            return 25
        elif mineral == "iron":
            return 5
        elif mineral == "stone":
            return 1