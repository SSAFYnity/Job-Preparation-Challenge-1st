class Solution {

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        int curTimeToSecond = h1 * 3600 + m1 * 60 + s1;
        int goalTimeToSecond = h2 * 3600 + m2 * 60 + s2;

        int alarm = 0;

        if (curTimeToSecond % 360 == 0 || curTimeToSecond % 360 == 12) alarm++;
//
        for (int time = curTimeToSecond; time < goalTimeToSecond; time++) {

            double[] curAngles = secondToAngle(time);
            double curAngleHour = curAngles[0];
            double curAngleMinute = curAngles[1];
            double curAngleSecond = curAngles[2];

            double[] nextAngles = secondToAngle(time + 1);
            double nextAngleHour = nextAngles[0];
            double nextAngleMinute = nextAngles[1];
            double nextAngleSecond = nextAngles[2];
            if (nextAngleHour == 0) nextAngleHour = 360;
            if (nextAngleMinute == 0) nextAngleMinute = 360;
            if (nextAngleSecond == 0) nextAngleSecond = 360;

            if (curAngleSecond < curAngleHour && nextAngleSecond >= nextAngleHour) alarm++;
            if (curAngleSecond < curAngleMinute && nextAngleSecond >= nextAngleMinute) alarm++;

            if (nextAngleHour == nextAngleMinute) alarm--;
        }

        return alarm;
    }

    public double[] secondToAngle(int seconds) {

        double angleSecond = seconds * 6 % 360;
        double angleMinute = seconds / 10.0d % 360;
        double angleHour = seconds / 120.0d % 360;

        return new double[] {angleHour, angleMinute, angleSecond};
    }
}