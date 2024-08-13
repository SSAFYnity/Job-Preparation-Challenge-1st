class Solution {
    public long solution(int n, int[] works) {

        int max = 50_000;
        int[] countingSort = new int[max + 1];

        for (int work : works) {
            countingSort[work]++;
        }

        for (int i = max; i != 0; i--) {
            if (countingSort[i] == 0) continue;
            while (n != 0 && countingSort[i] != 0) {
                countingSort[i]--;
                countingSort[i - 1]++;
                n--;
            }

            if (n == 0) break;
        }

        long answer = 0;
        for (int i = max; i != 0; i--) {
            if (countingSort[i] == 0) continue;
            long value = (long)i * i * countingSort[i];
            answer += value;
        }

        return answer;
    }
}