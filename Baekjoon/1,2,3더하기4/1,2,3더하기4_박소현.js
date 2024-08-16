const dp = Array.from(Array(10001), () => Array(4).fill(0));

dp[1][1] = 1;
dp[2][1] = 1;
dp[2][2] = 1;
dp[3][1] = 1;
dp[3][2] = 1;
dp[3][3] = 1;

for (let i = 4; i <= 10000; i++) {
  dp[i][1] = dp[i - 1][1];
  dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
  dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
}


// n을 1,2,3의 합으로 나타내는 방법의 수
const filePath = process.platform === "linux" ? "/dev/stdin" : "./15989.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");

const T = parseInt(input[0]);

for (let i = 1; i <= T; i++) {
  const N = parseInt(input[i]);
  let ans = dp[N][1] + dp[N][2] + dp[N][3];
  console.log(ans);
}
