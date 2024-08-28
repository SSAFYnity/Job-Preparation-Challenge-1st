const filePath = process.platform === "linux" ? "/dev/stdin" : "./15988.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");
const T = parseInt(input[0]);
const arr = input.slice(1).map(Number);
const maxNum = Math.max(...arr);

const dp = Array(maxNum + 1).fill(0);
dp[1] = 1; // 1
dp[2] = 2; // 1+1, 2
dp[3] = 4; // 1+1+1 1+2 2+1 3
dp[4] = 7; // 1+1+1+1 / 1+1+2 1+2+1 2+1+1  / 2+2  / 1+3 3+1
dp[5] = 11; // 1+1+1+1+1 / 1+1+1+2 1+1+2+1 1+2+1+1 2+1+1+1  / 2+2+1 2+1+2 1+2+2 / 1+1+3 1+3+1 3+1+1

for (let i = 4; i <= maxNum; i++) {
  dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
}

for (let t = 1; t <= T; t++) {
  const N = parseInt(input[t]);
  console.log(dp[N]);
}
