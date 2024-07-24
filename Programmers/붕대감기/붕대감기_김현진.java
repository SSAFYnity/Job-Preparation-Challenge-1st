class Solution {
	static int hp;
	static int time;
	static int i = 0;
	static int combo = 0;

	public int solution(int[] bandage, int health, int[][] attacks) {

		hp = health;
		time = attacks[0][0];
		int len = attacks.length;

		while (time <= attacks[len - 1][0]) {
			if (time == attacks[i][0]) {
				Attack(attacks);
				if (hp <= 0)
					return -1;
			} else {
				combo++;
				Heal(bandage, health, attacks);
			}
			time++;
		}
		return hp;
	}

	public void Attack(int[][] attacks) {
		hp -= attacks[i][1];
		combo = 0;
		i++;
	}

	public void Heal(int[] bandage, int health, int[][] attacks) {
		if (combo == bandage[0]) {
			hp += bandage[2];
			combo = 0;
		}

		if (hp < health)
			hp += bandage[1];
		if (hp > health)
			hp = health;
	}
}