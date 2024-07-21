class Solution {
    public int solution(int[] b, int h, int[][] a) {
        int t = b[0];
        int x = b[1];
        int y = b[2];

        int answer = h;
        int cnt = 0;
        int idx = 0;

        for (int time = 0; time <= a[a.length - 1][0]; time++) {
            if (idx < a.length && time == a[idx][0]) {
                answer -= a[idx][1];
                cnt = 0;
                idx++;
            } else {
                answer += x;
                cnt++;
                if (cnt == t) {
                    answer += y;
                    cnt = 0;
                }
            }
            if (answer > h) {
                answer = h;
            }
            if (answer <= 0) {
                return -1;
            }
        }
        return answer;
    }
}
