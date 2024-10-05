const fs = require('fs')
const io = process.platform === 'linux' ? '/dev/stdin' : './input.txt'

// 아기 상어 먹는 것
function bfs(sr, sc, place) {
  // 상, 좌, 하, 우 이동
  dr = [-1, 0, 1, 0]
  dc = [0, -1, 0, 1]
  
  // 초기화
  let weight = 2
  let fishToEat = 0
  let totalTime = 0

  while (true) {
    let queue  = [[sr, sc, 0]]
    let visited = Array.from({ length : N}, () => Array(N).fill(0))
    visited[sr][sc] = 1

    let fishes = []
    let foundFish = false
    let minDist = Number.MAX_SAFE_INTEGER

    while (queue.length > 0) {
      // 현재 정보
      let [cr, cc, dist] = queue.shift()
  
      for (let i = 0; i < 4; i++) {
        let nr = cr + dr[i]
        let nc = cc + dc[i]
  
        if (nr >= 0 && nr < N && nc >= 0 && nc < N && visited[nr][nc] === 0) {  // 범위 안이고 방문하지 않은 경우
          if (place[nr][nc] <= weight) {  // 방문 가능
            visited[nr][nc] = 1
            if (place[nr][nc] > 0 && place[nr][nc] < weight) {  // 먹기 가능
              if (dist + 1 < minDist) {  // 거리가 작음
                minDist = dist + 1
                fishes = [[nr, nc, dist + 1]]
                foundFish = true
              } else if (dist + 1 === minDist) {  // 거리가 똑같음
                fishes.push([nr, nc, dist + 1])
              }
            } else {  // 먹기 불가능
              queue.push([nr, nc, dist + 1])
            }
          }
        }
      }
    }
    if (foundFish) {  // 생선 찾음
      fishes.sort((a, b) => a[0] - b[0] || a[1] - b[1])  // 가장 위쪽, 왼쪽 우선
      const [nr, nc, dist] = fishes[0]
      place[nr][nc] = 0
      totalTime += dist
      fishToEat++
      // 고기먹고 성장 가능
      if (fishToEat === weight) {
        weight++
        fishToEat = 0
      }
      sr = nr
      sc = nc
    } else { // 생선 못찾음
      break
    }
  }
  return totalTime
}

const [n, ...arr] = fs.readFileSync(io).toString().trim().split('\n')

const N = Number(n)
const place = []

for (let i = 0; i < N; i++) {
  place.push(arr[i].trim().split(' ').map(Number))
}  


for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (place[i][j] === 9) {
      place[i][j] = 0
      const answer = bfs(i, j, place)
      console.log(answer)
      break
    }
  }      
}


// 판단 못한 것
// 초기 상어 위치 0으로 만드릭
// 가장 짧은 거리 파악 후 위쪽, 왼쪽 순으로 잡아먹기