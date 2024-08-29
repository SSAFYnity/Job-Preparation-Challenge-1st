# 시간 초과로 unsolved

import itertools
from bisect import bisect_right

def solution(dice):
    n = len(dice)
    dice_a = []  # A의 경우의 수
    dice_b = []  # B의 경우의 수
    visited = [0] * n

    # 1. A, B가 쓸 수 있는 주사위의 경우 구하기
    def dfs(level, array):
        if level == n // 2:
            temp = []
            for i in range(n):
                if i not in array:
                    temp.append(i)
            dice_a.append(array)
            dice_b.append(temp)
            return
        
        for i in range(n):
            if not visited[i]:
                visited[i] = 1
                dfs(level + 1, array + [i])
                visited[i] = 0

    # dfs 함수 실행     
    dfs(0, [])
    dice_num = len(dice_a)  # 주사위 배열의 길이

    max_win_count = 0
    best_combination = []

    # 2. 각 경우에 대해 모든 조합을 확인
    for i in range(dice_num):
        group_a = dice_a[i]
        group_b = dice_b[i]

        # A의 모든 조합의 합 계산 및 정렬
        all_combinations_a = list(itertools.product(*[dice[j] for j in group_a]))
        sum_a_list = sorted(sum(combo_a) for combo_a in all_combinations_a)

        win_count = 0

        # B의 조합에 대해 이진 탐색을 이용하여 A의 부분 합과 비교
        for combo_b in itertools.product(*[dice[j] for j in group_b]):
            sum_b = sum(combo_b)

            # A의 합 중 sum_b보다 큰 첫 번째 요소의 인덱스를 찾음
            win_count += len(sum_a_list) - bisect_right(sum_a_list, sum_b)

        # 최고 승리 횟수를 가진 조합 업데이트
        if win_count > max_win_count:
            max_win_count = win_count
            best_combination = [index + 1 for index in group_a]

    return best_combination