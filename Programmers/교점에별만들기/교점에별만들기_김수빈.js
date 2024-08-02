function solution(line) {
  const n = line.length
  let idx = 0
  const intersectionPoints = []
  const checkSize = [1000, 1000, -1000, -1000]  // 최소 x, 최소 y, 최대 x, 최대 y
  while (idx < n) {
    const [a1, b1, c1] = line[idx]
    if (line[idx] === 1)
    for (let i = idx; i < n; i++) {
      // 자기 자신이랑 같으면 넘어가기
      if (i === idx) {
        continue
      }
      const [a2, b2, c2] = line[i]
      // 행렬식 계산
      const determinant = a1 * b2 - a2 * b1
      // 평행할 경우 넘어가기
      if (determinant === 0) {
        continue
      }
      // 크래머의 법칙으로 해 구하기
      const intersectionX = (c1 * b2 - c2 * b1) / determinant
      const intersectionY = (a1 * c2 - a2 * c1) / determinant
      if (Number.isInteger(intersectionX) && Number.isInteger(intersectionY)) {
        return 
      }
      
    }
  }
  return answer;
}