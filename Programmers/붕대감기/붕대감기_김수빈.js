// bandage [시전 시간, 초당 회복량, 추가 회복량] 
// health 최대 체력
// attacks 몬스터 [공격 시간, 피해량] 형태의 길이가 2인 정수 배열 (2차원)
// 살아남으면 체력 return 죽으면 1 return

function solution(bandage, health, attacks) {
  const maxTime = attacks[attacks.length - 1][0]  // 최대 시간
  const maxHealth = health
  let healCheckTime = 0  // 추가 회복확인시간
  let attackIdx = 0  // 공격 인덱스
  for (let i = 0; i <= maxTime; i++) {
    // attackIdx 길이 이내이고 공격 시간일 경우
    if (attackIdx < attacks.length && i === attacks[attackIdx][0]) {
      health -= attacks[attackIdx][1]
      // 만약 공격으로 체력 0 이하일 경우 사망
      if (health <= 0) {
        return -1
      }
      // 추가 회복확인시간 초기화
      healCheckTime = 1
      // 공격 인덱스 추가
      attackIdx++
      // 추가 회복시간 도달
    } else if (healCheckTime === bandage[0]) {
      if (health + bandage[2] + bandage[1] > maxHealth) {
        health = maxHealth
      } else {
        health += bandage[2] + bandage[1]
      }
      // 추가 회복확인시간 초기화
      healCheckTime = 0
      // 그 외 나머지
    } else {
      if (health + bandage[1]  > maxHealth) {
        health = maxHealth
      } else {
        health += bandage[1]
      }
      healCheckTime++
    }
  }

  if (health <= 0) {
    return -1
  } else {
    return health
  }
}

console.log(solution([1, 1, 1], 20, [[1, 5], [4, 1]]))