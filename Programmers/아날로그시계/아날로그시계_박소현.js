function checkMeet(time) {

	// 만나는 횟수 (초-분, 초-시)
	const secondMinute = Math.floor(time * 59 / 3600); 
	const secondHour = Math.floor(time * 719 / 43200);
	
	// 12시일때 전체 시간 중 2번의 정각, 1번의 정각 판단(셋다 겹치는 경우를 빼줌)
	const twelve = time >= 43200 ? 2 : 1;
	
	return secondMinute + secondHour - twelve;
}

// 초-분/초-시 판단 알람 울린 횟수 판단
function solution(h1, m1, s1, h2, m2, s2) {
	
	// 초단위 시간으로 변환
	const start = h1 * 3600 + m1 * 60 + s1;
	const end = h2 * 3600 + m2 * 60 + s2
	
	
	// 시작 시간 하나라도 알람여부
	let plus = 0
	if ((start * 59) % 3600 === 0 || (start * 719) % 43200 === 0){
			plus = 1
	}
	
	//  끝까지의 - 시작까지의 + 시작시간 알람여부
	const answer = checkMeet(end) - checkMeet(start) + plus
	
	return answer;
}




