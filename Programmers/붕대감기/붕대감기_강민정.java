class Solution {
    /*
        bandage: 붕대 감기 기술의 시전 시간, 1초당 회복량, 추가 회복량을 담은 1차원 정수 배열
        health: 최대 체력
        attacks: 몬스터의 공격 시간과 피해량
    */
    public int solution(int[] bandage, int health, int[][] attacks) {
        int curHealth = health;     // 초기 체력
        int attacksIdx = 0;         // 몬스터의 공격 배열 인덱스
        int curTime = 0;            // 현재 시간
        int curBandage = 0;         // 연속 붕대 감기 횟수
        
        while (curTime < attacks[attacks.length - 1][0]) { // 마지막 공격 시간까지 반복
            curTime++; // 시간 증가
            if (attacksIdx < attacks.length && attacks[attacksIdx][0] == curTime) { // 공격을 당하는 시간
                curHealth -= attacks[attacksIdx][1]; // 공격 받은 만큼 체력이 깎임
                attacksIdx++; // 그 다음 공격으로 넘어가기
                curBandage = 0; // 연속 붕대 감기 성공 시간이 초기화
                if (curHealth <= 0) { // 죽음
                    return -1;
                }
            } else {
                curBandage++; // 연속 붕대 감기 횟수 증가
                if (curBandage == bandage[0]) { // 연속 체력 회복 성공
                    curHealth += bandage[2]; // 추가 체력 회복
                    curBandage = 0; // 연속 붕대 감기 초기화
                }
                curHealth += bandage[1]; // 1초마다 회복하는 체력만큼 증가
                if (curHealth >= health) { // 최대 체력 초과 방지
                    curHealth = health;
                }
            }
        }
        
        return curHealth; // 모든 공격이 끝난 직후 남은 체력
    }
}
