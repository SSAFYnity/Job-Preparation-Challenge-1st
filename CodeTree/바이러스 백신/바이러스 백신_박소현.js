const dx = [1, 0, -1, 0];
const dy = [0, -1, 0, 1];

function isLeftVirus(arr) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (arr[i][j] === 0) return false;
    }
  }
  return true;
}

function deleteVirus(combination, N, arr) {
  // 제거된 바이러스
  let deleteCnt = 0;
  let visited = new Array(N).fill().map(() => new Array(N).fill(0));
  let q = [...combination];

  while (q.length > 0) {
    let [x, y] = q.shift();

    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
      // 벽이거나 이미 방문했다면 넘어가기
      if (arr[nx][ny] === 1 || visited[nx][ny] !== 0) continue;

      // =============시간 업데이트 =============
      visited[nx][ny] = visited[x][y] + 1;

      // 이미 찾은 값보다 크다면 더이상 진행할 필요 없으니 바로 return
      if (visited[nx][ny] >= ans) return ans;

      // 바이러스일 경우 바이러스 제거 표시
      if (arr[nx][ny] === 0) {
        arr[nx][ny] = 3;
        deleteCnt++;
      }

      // 전부 제거됐을 경우 return
      if (deleteCnt === virusCnt) return visited[nx][ny];

      q.push([nx, ny]);
    }
  }

  return Infinity;
}

// 조합구하기
function getCombinations(arr, num) {
  const results = [];
  if (num === 1) {
    return arr.map((val) => [val]);
  }
  arr.forEach((fixed, idx, origin) => {
    const rest = origin.slice(idx + 1);
    const combinations = getCombinations(rest, num - 1);
    combinations.forEach((combination) => {
      results.push([fixed, ...combination]);
    });
  });
  return results;
}

// 0 : 바이러스, 1: 벽, 2: 병원
// 모든 바이러스를 없대는데 필요한 최소 시간
// 모든 바이러스를 없앨 수 없다면 -1
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./test.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
let arr = input.slice(1).map((l) => l.split(" ").map(Number));

//  바이러스 갯수
let virusCnt = 0;
let hospitals = [];

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (arr[i][j] === 0) {
      virusCnt++;
    }
    if (arr[i][j] === 2) {
      hospitals.push([i, j]);
    }
  }
}
// 조합을 만들어서
let combinations = getCombinations(hospitals, M);

// 각 조합마다 최솟값 업데이트
let ans = Infinity;
combinations.forEach((combination) => {
  // 매번 새로운 arr 필요
  let temp = JSON.parse(JSON.stringify(arr));
  combination.forEach((hospital) => {
    temp[hospital[0]][hospital[1]] = -2;
  });

  let result = deleteVirus(combination, N, temp);
  ans = Math.min(ans, result);
});

// 처음부터 바이러스가 없다면
if (isLeftVirus(arr)) {
  console.log(0);
  return;
}

console.log(ans === Infinity ? -1 : ans);
