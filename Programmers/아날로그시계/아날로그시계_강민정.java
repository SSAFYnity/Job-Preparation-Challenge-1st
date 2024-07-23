class 아날로그시계_강민정 {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startSecond = transform(h1, m1, s1);        // 시작 시각을 초로 변환
        int endSecond = transform(h2, m2, s2);          // 끝나는 시각을 초로 변환
        int startSecondDuplicate = 0;       // 시작 시각에 알람이 울릴 수 있으면 1 할당
        
        if((startSecond * 59 % 3600 == 0) || (startSecond * 719 * 43200 == 0)) {    // 분침 또는 시침이 초침과 겹침
            startSecondDuplicate++;
        }
        
        return calDuplicateCnt(endSecond) - calDuplicateCnt(startSecond) + startSecondDuplicate;
    }
    
    /*
        초로 변환
        h: 시
        m: 분
        s: 초
    */
    public int transform(int h, int m, int s) {
        return 3600 * h + 60 * m + s;
    }
    
    /*
        겹치는 횟수 세기
    */
    public int calDuplicateCnt(int second) {
        int cntMinute = second * 59 / 3600;     // 분침은 1바퀴 돌면 초침과 59번 겹칠 수 있음
        int cntHour = second * 719 / 43200;    // 시침은 1바퀴 돌면 초침과 719(60 * 12 - 1)번 겹칠 수 있음
        int duplicate = 1;
        
        if(second >= 43200) {       // 정오가 중복되어서 빼주기
            duplicate = 2;
        }
        
        return cntMinute + cntHour - duplicate;
    }
}