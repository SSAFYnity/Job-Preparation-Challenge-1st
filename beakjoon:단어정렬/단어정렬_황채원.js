let input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let N = parseInt(input[0]);
let words = new Set();

for (let i = 1; i <= N; i++) {
  words.add(input[i]);
}

let arr = Array.from(words).map(word => ({
  word: word,
  length: word.length,
}));
arr.sort((a, b) => {
  if (a.length === b.length) {
    return a.word.localeCompare(b.word);
  }
  return a.length - b.length;
});

for (let j = 0; j < arr.length; j++) {
  console.log(arr[j]["word"]);
}
