from itertools import combinations

def solution(dice):
    answer = 0
    most_win = 0
    for mine in combinations([i + 1 for i in range(len(dice))], len(dice) // 2):
        my_dice = [0]
        your_dice = [0]
        for i in range(1, len(dice) + 1):
            if i in mine:
                new_dice = []
                for d1 in my_dice:
                    for d2 in dice[i - 1]:
                        new_dice.append(d1 + d2)
                my_dice = new_dice
            else:
                new_dice = []
                for d1 in your_dice:
                    for d2 in dice[i - 1]:
                        new_dice.append(d1 + d2)
                your_dice = new_dice
        my_dict = dict()
        your_dict = dict()
        for m in my_dice:
            if m in my_dict.keys():
                my_dict[m] += 1
            else:
                my_dict[m] = 1
        for y in your_dice:
            if y in your_dict.keys():
                your_dict[y] += 1
            else:
                your_dict[y] = 1
        win = 0
        for m, md in my_dict.items():
            for y, yd in your_dict.items():
                if m > y:
                    win += md * yd
        if win > most_win:
            most_win = win
            answer = list(mine)
    return answer