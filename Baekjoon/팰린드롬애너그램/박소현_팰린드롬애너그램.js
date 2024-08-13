const filePath = process.platform === "linux" ? "/dev/stdin" : "./30458.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");

const N = parseInt(input[0]);
const arr = input[1];

let cnt = new Map();

for (let i = 0; i < N; i++) {
  const char = arr[i];
  cnt.set(char, (cnt.get(char) || 0) + 1);
}

if (N % 2) {
  const mid = arr[Math.floor(N / 2)];
  cnt.set(mid, cnt.get(mid) - 1);
}

for (let val of cnt.values()) {
  if (val % 2) {
    console.log("No");
    process.exit(0);
  }
}

console.log("Yes");
