import java.util.*;

class Solution {
    public int solution(int[] p, String[] m) {
        int ans = 0;
        List<int[]> sortArr = new ArrayList<>();
        int[][] tired = {
                {1, 1, 1},
                {5, 1, 1},
                {25, 5, 1}
        };

        int len = (int) Math.ceil(m.length / 5.0);
        int maxLen = p[0] + p[1] + p[2];

        for (int i = 0; i < len; i++) {
            if (i >= maxLen) break;
            int[] arr = {0, 0, 0};

            for (int j = i * 5; j < (i + 1) * 5 && j < m.length; j++) {
                switch (m[j]) {
                    case "diamond":
                        arr[0]++;
                        break;
                    case "iron":
                        arr[1]++;
                        break;
                    default:
                        arr[2]++;
                        break;
                }
            }
            sortArr.add(arr);
        }

        sortArr.sort((a, b) -> {
            if (a[0] == b[0]) {
                if (a[1] == b[1]) {
                    return b[2] - a[2];
                } else {
                    return b[1] - a[1];
                }
            } else {
                return b[0] - a[0];
            }
        });

        for (int[] item : sortArr) {
            int d = item[0], i = item[1], s = item[2];
            int idx = 0;

            if (p[0] != 0) idx = 0;
            else if (p[1] != 0) idx = 1;
            else if (p[2] != 0) idx = 2;

            if (p[idx] != 0) {
                ans += tired[idx][0] * d;
                ans += tired[idx][1] * i;
                ans += tired[idx][2] * s;
                p[idx]--;
            }
        }

        return ans;
    }
}
