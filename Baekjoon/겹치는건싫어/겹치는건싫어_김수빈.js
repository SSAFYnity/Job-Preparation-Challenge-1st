const fs = require('fs')
const io = process.platform === "linux" ? "/dev/stdin" : "./input.txt"

const input = fs.readFileSync(io).toString().trim().split('\n')
const [N, K] = input[0].split(' ').map(Number)
const arr = input[1].split(' ').map(Number)

let idx = 0
let maxCnt = 0
let flag = false

while (true) {
  const numbers = new Map()
  let cnt = 0
  for (let i = idx; i < arr.length; i++) {
    const int = arr[i]
    // 만약 해당 숫자를 이미 넣었을 경우
    if (numbers.get(int)) {
      numbers.get(int).push(i)  // 해당 숫자의 index 삽입
    // 해당 숫자가 아예 없는 경우
    } else {
      numbers.set(int, [i])  // 헤당 숫자의 배열 만들고 index 삽입 
    }
    // 해당 배열이 K개 초과한 경우
    if (numbers.get(int).length > K) {
      // 해당 cnt가 maxCnt보다 큰 경우 교환
      if (maxCnt < cnt) {
        maxCnt = cnt
      }
      // idx는 K개 초과한 경우의 idx 바로 뒤에서 시작
      idx = numbers.get(int)[0] + 1
      break
    }
    cnt++
    // 수가 끝까지 갔다면 flag 변수 바꾸기 및 maxCnt 체크
    if (i === arr.length - 1) {
      flag = true
      if (maxCnt < cnt) {
        maxCnt = cnt
      }
    }
  }
  if (flag) {
    break
  }
}

console.log(maxCnt)