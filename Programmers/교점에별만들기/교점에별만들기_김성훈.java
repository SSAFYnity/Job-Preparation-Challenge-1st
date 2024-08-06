import java.util.*;

class Solution {

    class Point {

        long x, y;

        Point(double x, double y) {
            this.x = (long)x; this.y = (long)y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point p = (Point)obj;
                return p.x == x && p.y == y;
            }
            else return false;
        }

        @Override
        public int hashCode() {
            return (int)(y * 1000007 + x);
        }
    }

    public String[] solution(int[][] line) {

        int lineCnt = line.length;

        long topLeftX = Long.MAX_VALUE;
        long topLeftY = Long.MAX_VALUE;
        long bottomRightX = Long.MIN_VALUE;
        long bottomRightY = Long.MIN_VALUE;

        HashSet<Point> meetingSet = new HashSet<>();

        for (int i = 0; i < lineCnt; i++) {
            for (int j = 0; j < lineCnt; j++) {

                if (i == j) continue;

                double a = line[i][0];
                double b = line[i][1];
                double e = line[i][2];

                double c = line[j][0];
                double d = line[j][1];
                double f = line[j][2];

                double adbc = a * d - b * c;
                if (adbc == 0) continue;

                double x = (b * f - e * d) / adbc;
                double y = (e * c - a * f) / adbc;

                if (x % 1 != 0 || y % 1 != 0) continue;

                meetingSet.add(new Point(y, x));

                topLeftX = Math.min((long)x, topLeftX);
                topLeftY = Math.min((long)y, topLeftY);
                bottomRightX = Math.max((long)x, bottomRightX);
                bottomRightY = Math.max((long)y, bottomRightY);
            }
        }

        int x = (int)(bottomRightX - topLeftX + 1);
        int y = (int)(bottomRightY - topLeftY + 1);

        boolean[][] res = new boolean[y][x];

        for (Point meeting : meetingSet) {
            int curY = (int)(meeting.x - topLeftY);
            int curX = (int)(meeting.y - topLeftX);
            res[curY][curX] = true;
        }

        StringBuilder sb = new StringBuilder();
        String[] answer = new String[y];

        for (int i = 0; i < y; i++) {
            for (boolean b : res[y - i - 1]) {
                if (b) sb.append('*');
                else sb.append('.');
            }
            answer[i] = sb.toString();
            sb.setLength(0);
        }

        return answer;
    }
}