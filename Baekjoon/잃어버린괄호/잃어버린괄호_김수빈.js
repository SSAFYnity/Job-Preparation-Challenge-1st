const fs = require('fs')
const io = process.platform === 'linux' ? 'dev/stdin' : './input.txt'

const input = fs.readFileSync(io).toString().trim()

// 일단 마이너스로 다 나누기
const minusArr = input.split('-')
// 첫 번째 숫자만 더하기
let result = minusArr[0].split('+').map(Number).reduce((acc, cur) => acc + cur, 0)
// 나머지는 다 빼기
for (let i = 1; i < minusArr.length; i++) {
  const temp = minusArr[i].split('+').map(Number).reduce((acc, cur) => acc + cur, 0)
  result -= temp
}


console.log(result);