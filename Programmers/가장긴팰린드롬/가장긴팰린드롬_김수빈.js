function solution(s) {
  let n = s.length
  let dp = Array.from(Array(n), () => Array(n).fill(false))
  let maxLength = 1

  // 문자 하나는 다 팰린드롬
  for (let i = 0; i < n; i++) {
    dp[i][i] = true;
  }

  // 문자가 연속적으로 있는 경우
  for (let i = 0; i < n - 1; i++) {
    if (s[i] === s[i + 1]) {
      dp[i][i + 1] = true
      maxLength = 2
    }
  }

  // 3개부터 n개까지 세기
  for (let len = 3; len <= n; len++) {
    // i는 시작
    for (let i = 0; i < n - len + 1; i++) {
      // j는 끝
      let j = i + len - 1
      if (s[i] === s[j] && dp[i + 1][j - 1]) {
        dp[i][j] = true
        maxLength = len
      }
    }
  }

  return maxLength
}

console.log(solution('abcdcba'))