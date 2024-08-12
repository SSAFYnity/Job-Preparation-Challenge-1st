function solution(n, works) {
  if (works.reduce((a, b) => a + b) <= n) return 0;

  works.sort((a, b) => a - b);

  while (n > 0) {
    const maxVal = works[works.length - 1];

    for (let i = works.length - 1; i >= 0; i--) {
      if (works[i] >= maxVal) {
        works[i]--;
        n--;
      }
      if (n === 0) break;
    }
  }

  return works.reduce((a, b) => a + Math.pow(b, 2), 0);
}
