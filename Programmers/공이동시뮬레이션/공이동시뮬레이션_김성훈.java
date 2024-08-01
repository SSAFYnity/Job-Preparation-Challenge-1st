import java.util.*;

class Solution {

    public long solution(int n, int m, int x, int y, int[][] queries) {

        long left, right, top, bottom;
        left = right = y;
        top = bottom = x;

        int size = queries.length - 1;

        for (int i = size; i >= 0; i--) {

            int dir = queries[i][0];
            int move = queries[i][1];

            switch (dir) {
                case 0:
                    if (left != 0) left += move;
                    right = Math.min(right + move, m - 1);
                    break;

                case 1:
                    if (right != m - 1) right -= move;
                    left = Math.max(0, left - move);
                    break;

                case 2:
                    if (top != 0) top += move;
                    bottom = Math.min(bottom + move, n - 1);
                    break;

                case 3:
                    if (bottom != n - 1) bottom -= move;
                    top = Math.max(0, top - move);
                    break;
            }

            if (left >= m || right < 0 || top >= n || bottom < 0) return 0;
        }

        return (right - left + 1) * (bottom - top + 1);
    }
}