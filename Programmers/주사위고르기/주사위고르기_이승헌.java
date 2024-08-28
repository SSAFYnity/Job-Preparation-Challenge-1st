package 주사위고르기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 주사위고르기_이승헌 {

    static int max;
    static boolean[] answer;

    public static void main(String[] args) {
        int[] solution = solution(
                new int[][]{{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}});
        System.out.println("solution = " + Arrays.toString(solution));
        int[] solution1 = solution(new int[][]{{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}});
        System.out.println("solution = " + Arrays.toString(solution1));
        int[] solution2 = solution(
                new int[][]{{40, 41, 42, 43, 44, 45}, {43, 43, 42, 42, 41, 41}, {1, 1, 80, 80, 80, 80},
                        {70, 70, 1, 1, 70, 70}});
        System.out.println("solution = " + Arrays.toString(solution2));

    }


    public static int[] solution(int[][] dice) {
        max = 0;
        int max = dice.length / 2;
        // choice
        answer = new boolean[dice.length];
        choice(dice, max, 0, 0, new boolean[dice.length]);

        int[] result = new int[dice.length / 2];
        int count = 0;
        for (int idx = 0; idx < answer.length; idx++) {
            if (answer[idx]) {
                result[count++] = idx + 1;
            }
        }

        return result;
    }

    private static void choice(int[][] dice, int max, int curIdx, int selectNum, boolean[] select) {
        if (curIdx == dice.length + 1 || dice.length - curIdx < max - selectNum) {
            return;
        }

        if (selectNum == max) {
            // 돌리기
            cal(dice, select);
            return;
        }
        choice(dice, max, curIdx + 1, selectNum, select);
        select[curIdx] = true;
        choice(dice, max, curIdx + 1, selectNum + 1, select);
        select[curIdx] = false;
    }

    private static void cal(int[][] dice, boolean[] select) {
        List<Integer> AList = new ArrayList<>();
        List<Integer> BList = new ArrayList<>();

        for (int idx = 0; idx < dice.length; idx++) {
            if (select[idx]) {
                sumList(dice[idx], AList);
            } else {
                sumList(dice[idx], BList);
            }
        }

        Collections.sort(AList);
        Collections.sort(BList);

        int count = 0;
        int left = -1;
        int size = AList.size();
        for (int ALeft = 0; ALeft < size; ALeft++) {
            int right = size;
            int mid = 0;
            while (left + 1 < right) {
                mid = (left + right) / 2;

                if (AList.get(ALeft) <= BList.get(mid)) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            count += right;
        }

        if (max < count) {
            max = count;
            System.arraycopy(select, 0, answer, 0, select.length);
        }
    }

    private static void sumList(int[] dice, List<Integer> AList) {
        List<Integer> temp;
        if (AList.isEmpty()) {
            for (int die : dice) {
                AList.add(die);
            }
        }else{
            temp = new ArrayList<>(AList);
            AList.clear();
            for (int i : temp) {
                for (int die : dice) {
                    AList.add(die + i);
                }
            }
        }
    }
}
