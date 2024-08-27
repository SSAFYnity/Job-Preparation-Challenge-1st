// 같은 언소가 K개 이하(동시중복 가능)로 들어있는 최장 연속 부분 수열의 길이
const filePath = process.platform === "linux" ? "/dev/stdin" : "./20922.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");

const [N, K] = input[0].split(" ").map(Number);
const arr = input[1].split(" ").map(Number);
const check = Array(100001).fill(0);

let left = 0;
let right = 0;
let cnt = 0;


while (right < N) {

  check[arr[right]] += 1;
 
  while (check[arr[right]] > K) {
    check[arr[left]] -= 1;
    left++;
  }

	right++

  cnt = Math.max(cnt, right - left);
}

console.log(cnt)