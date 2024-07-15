function dfs(temp, discountList, depth) {
	if (depth === temp.length) {
			discountList.push(temp.slice());
			return;
	}
	for (let ratio of [10,20,30,40]) {
			temp[depth] = ratio;
			dfs(temp, discountList, depth + 1);
			temp[depth] = 0;
	}
}

function solution(users, emoticons) {
	let answer = [0, 0];
	
	// 할인 조합 생성
	let temp = Array(emoticons.length).fill(0)
	const discountList = [];
	dfs(temp, discountList, 0);

	for (let d = 0; d < discountList.length; d++) {
			let plusUser = 0;
			let total = 0;
			let now = discountList[d]

			for (let user of users) {
					let cost = 0;
					for (let i = 0; i < emoticons.length; i++) {
							if (now[i] >= user[0]) {
									cost += emoticons[i] * ((100 - now[i]) / 100);
							}
					}
					
					// 플러스유저 전환 판별
					if (user[1] <= cost) {
							plusUser++;
					} else {
							total += cost;
					}
			}
			
			// 조건에 맞는 최적값 업데이트
			if (plusUser > answer[0] || (plusUser === answer[0] && total > answer[1])) {
					answer = [plusUser, total];
			}
	}

	return answer;
}


