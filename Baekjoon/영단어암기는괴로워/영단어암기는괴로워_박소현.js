const filePath = process.platform === "linux" ? "/dev/stdin" : "./20920.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
const wordDict = new Map();

for (let i = 1; i <= N; i++) {
  const word = input[i].trim();
  if (word.length < M) continue;
  wordDict.set(word, (wordDict.get(word) | 0) + 1);
}

const arr = Array.from(wordDict).sort((a, b) => {
  if (a[1] !== b[1]) {
    return b[1] - a[1];
  } else if (a[0].length !== b[0].length) {
    return b[0].length - a[0].length;
  } else {
    return a[0].localeCompare(b[0]);
  }
});

const ans = [];
arr.forEach((val) => {
  ans.push(val[0]);
});

console.log(ans.join("\n"));
