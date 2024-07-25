class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startSecond = toSecond(h1, m1, s1);
        int endSecond = toSecond(h2, m2, s2);
        
        /*
        1. 분침 1바퀴(60분=3600초)도는 동안 초침과 60-1=59번 겹침
        2. 시침 1바퀴(12시간=720분=43200초)도는 동안 초침과 60*12-1=719번 겹침
        */
        int calc = endSecond * 59/3600 + endSecond * 719/43200
            - startSecond * 59/3600 - startSecond * 719/43200;
        
        if (startSecond >= 43200) {
            calc += 1;
        }
        if (endSecond >= 43200) {
            calc -= 1;
        }
        if (startSecond * 59 % 3600 == 0 || startSecond * 719 % 43200 == 0) {
            calc += 1;
        }
                        
        return calc;
    }
        
    static int toSecond(int h, int m, int s) {
        return h * 3600 + m * 60 + s;
    }
}
