// 괄호를 자유롭게 써서 식의 결과값을 최소로
const filePath = process.platform === "linux" ? "/dev/stdin" : "./1541.txt";
let input = require("fs").readFileSync(filePath).toString().trim().split("\n");

const arr = input[0];
let ans = 0;

const plusFormula = arr.split("-");

ans += plusFormula[0].split("+").reduce((acc, val) => acc + Number(val), 0);

for (let i = 1; i < plusFormula.length; i++) {
  let temp = plusFormula[i]
    .split("+")
    .reduce((acc, val) => acc + Number(val), 0);
  ans -= temp;
}
console.log(ans);
