// 연속된 수들의 부분합 중에 합이 S이상이 되는것중, 가장짧은 것의 길이
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./1806.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [N, S] = input[0].split(" ").map(Number);
const arr = input[1].split(" ").map(Number);

let start = 0;
let end = 0;

let ans = Infinity;
let sumNum = 0;

while (end < N) {
  sumNum += arr[end];

  while (sumNum >= S) {
    ans = Math.min(ans, end - start + 1);
    sumNum -= arr[start];
    start++;
  }

  end++;
}
console.log(ans === Infinity ? 0 : ans);
