function toNumber(N) {
  const stickVal = [0, 0, 1, 7, 4, 2, 6, 8, 10];
  // 갯수마다 만들수 있는 수
  const dp = Array(101).fill(Infinity);

  for (let i = 1; i < 9; i++) {
    dp[i] = stickVal[i];
  }

  for (let i = 9; i <= 100; i++) {
    dp[i] = dp[i - 2] * 10 + stickVal[2];
    for (let j = 3; j < 8; j++) {
      dp[i] = Math.min(dp[i], dp[i - j] * 10 + stickVal[j]);
    }
  }

  const minVal = dp[N];
  let maxVal = "";

  // 짝수
  if (N % 2 === 0) {
    for (let i = 0; i < N / 2; i++) {
      maxVal += "1";
    }
  } else {
    maxVal += "7";
    for (let i = 0; i < (N - 3) / 2; i++) {
      maxVal += "1";
    }
  }

  return [minVal, maxVal];
}

const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./3687.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

for (const tc of input.slice(1)) {
  const [minNum, maxNum] = toNumber(Number(tc));
  console.log(minNum, maxNum);
}
