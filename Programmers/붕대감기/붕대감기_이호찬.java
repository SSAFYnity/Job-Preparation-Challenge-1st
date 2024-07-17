// t초 동안 1초마다 -> x 체력 회복
// t초 연속 성공 -> y 추가 체력 회복, 성공 시간 0초 초기화
// 최대 체력까지만 회복 가능

// n초에 공격을 받으면, 정해진 피해량 만큼 체력 감소 및 성공 시간 0초 초기화
// n+1초 부터 붕대 감기 시작
// 체력이 0이하가 되면 사망 -> 회복 불가능

// 모든 공격이 끝난 후 남은 체력을 return, 죽었다면 -1 return

class Solution {
	public int solution(int[] bandage, int health, int[][] attacks) {
		int time = 0;
		int curHealth = health; // 현재 체력
		for (int i = 0; i < attacks.length; i++) {
			// 체력 회복: 현재 체력 + ((현재 공격 시간 - 1) - 마지막 공격 시간)차이만큼 붕대 감기
			int subTime = (attacks[i][0] - 1) - time;
			curHealth += subTime * bandage[1]; // 1초당 회복량
			curHealth += (subTime / bandage[0]) * bandage[2]; // 추가 회복량
			curHealth = (curHealth > health) ? health : curHealth; // 최대 체력까지만 회복 가능

			// 몬스터 공격
			curHealth -= attacks[i][1];
			if (curHealth <= 0) { // 캐릭터 사망
				return -1;
			}

			time = attacks[i][0];
		}
		return curHealth;
	}
}