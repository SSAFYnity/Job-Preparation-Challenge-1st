// 최대수익
const filePath = process.platform === "linux" ? "/dev/stdin" : "./14501.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");

const N = parseInt(input[0]);
const arr = input.slice(1).map((l) => l.split(" ").map(Number));

const dp = Array(N + 1).fill(0);

for (let i = 0; i < N; i++) {
  const [T, P] = arr[i];
  dp[i + 1] = Math.max(dp[i], dp[i + 1]);
  if (i + T > N) continue;
  dp[i + T] = Math.max(dp[i] + P, dp[i + T]);
}

console.log(Math.max(...dp));
