function getLength(s, left, right) {
  const N = s.length;

  while (left >= 0 && right < N && s[left] === s[right]) {
    left--;
    right++;
  }
  return right - left - 1;
}

function solution(s) {
  let ans = 1;

  for (let i = 0; i < s.length; i++) {
    // 홀수
    const len1 = getLength(s, i, i);
    // 짝수
    const len2 = getLength(s, i, i + 1);

    ans = Math.max(ans, len1, len2);
  }

  return ans;
}
