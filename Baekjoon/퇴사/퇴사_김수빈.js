const fs = require('fs')
const io = process.platform === 'linux' ? '/dev/stdin' : './input.txt'

const input = fs.readFileSync(io).toString().trim().split('\n')

const N = Number(input[0])
const arr = []

for (let i = 1; i < input.length; i++) {
  const [T, P] = input[i].split(' ').map(Number)
  arr.push([T, P])
}

maxProfit = Array(N + 1).fill(0)

for (let i = 1; i < N + 1; i++) {
  for (let j = 0; j < i; j++) {
    // 상담 진행 가능 경우
    if (j + arr[j][0] <= i) {
      maxProfit[i] = Math.max(maxProfit[i], maxProfit[j] + arr[j][1])
    }
  }
}

console.log(maxProfit[N])