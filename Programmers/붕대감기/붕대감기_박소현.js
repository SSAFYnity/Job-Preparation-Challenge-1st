function solution(bandage, health, attacks) {
	const [t, sec, plus] = bandage
	let now = health
	let lastTime = 0

	for (const [time, damage] of attacks) {
			// 이전 공격 이후 현재 공격 시간까지의 시간
			let calculatedTime = time - lastTime - 1
			
			// 회복
			now += calculatedTime * sec
			// 추가 회복
			let successed = Math.floor(calculatedTime / t)
			now += successed * plus;

			// 최대 체력 관리
			if (now > health) {
					now = health;
			}

			lastTime = time;

			// 깎고 체력이 0이하로 떨어지면 리턴
			now -= damage;
			if (now <= 0) {
					return -1;
			}
	}

	return now;
}