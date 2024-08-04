// 두사람은 합승이 가능함
// s에서 출발해서 각각 도착지점(a, b)까지 이동했을때 최저 예상 총 택시요금
function solution(n, s, a, b, fares) {

  // 인접 행렬
  const adjArr = Array.from({ length: n }, () => Array(n).fill(Infinity));

  // 양쪽방향 모두 입력
  for (const [start, end, fare] of fares) {
    adjArr[start - 1][end - 1] = fare;
    adjArr[end - 1][start - 1] = fare;
  }

  // 자기 자신은 0으로
  for (let i = 0; i < n; i++) {
    adjArr[i][i] = 0;
  }

  // 플로이드-워셜
  for (let k = 0; k < n; k++) {
    for (let i = 0; i < n; i++) {
      for (let j = 0; j < n; j++) {
        adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);
      }
    }
  }

  // 합승을 멈추는 시기를 탐색하며 최솟값 업데이트
  let answer = Infinity;
  for (let i = 0; i < n; i++) {
    answer = Math.min(
      answer,
      adjArr[s - 1][i] + adjArr[a - 1][i] + adjArr[b - 1][i]
    );
  }

  return answer;
}
