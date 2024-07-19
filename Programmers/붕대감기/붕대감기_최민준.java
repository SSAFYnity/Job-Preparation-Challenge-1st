import java.util.*;
import java.io.*;

class Solution {
	public int solution(int[] bandage, int health, int[][] attacks) {
		int curHealth = health;
		int t = bandage[0]; // 시전시간
		int x = bandage[1]; // 초당회복량
		int y = bandage[2]; // 추가 회복량

		int preTime = attacks[0][0]; // 이전 시간
		curHealth -= attacks[0][1];
		if (curHealth <= 0) return -1;

		// 1. 총 공격 횟수만큼 반복
		for (int i = 1; i < attacks.length; i++) {
			int attackTime = attacks[i][0];
			int attackDamage = attacks[i][1];

			int diffTime = attackTime - preTime - 1;
			preTime = attackTime;

			int healAdd = (diffTime / t) * y; // 추가 회복량 계산
			int totalHeal = healAdd + (diffTime * x); // 총 회복량
			curHealth += totalHeal;

			// 현재체력이 최대체력 넘을경우 최대체력으로 set
			if (curHealth >= health)
				curHealth = health;

			curHealth -= attackDamage;
			// 현재 체력이 0이하 되면 바로 게임 끝
			if (curHealth <= 0) {
				return -1;
			}

		}
		return curHealth;
	}
}