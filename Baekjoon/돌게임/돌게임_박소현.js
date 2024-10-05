const filePath = process.platform === "linux" ? "/dev/stdin" : "./9655.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");

const N = input[0];

const ans = N % 2 === 0 ? "CY" : "SK";
console.log(ans)