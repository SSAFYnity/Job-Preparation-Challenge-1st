const dx = [-1, 0, 1, 0];
const dy = [0, -1, 0, 1];

function findFish(arr, N, babyShark) {
  let [bx, by, bSize] = babyShark;

  const q = [[bx, by, 0]];
  const visited = Array(N)
    .fill()
    .map(() => Array(N).fill(false));
  visited[bx][by] = true;

  let edibleFish = [];

  while (q.length > 0) {
    const [x, y, dist] = q.shift();

    for (let d = 0; d < 4; d++) {
      let nx = x + dx[d];
      let ny = y + dy[d];

      if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

      // 물고기가 작거나 같을 경우만 이동
      if (arr[nx][ny] <= bSize) {
        visited[nx][ny] = true;
        q.push([nx, ny, dist + 1]);

        // 먹을 수 있는 생선이 있을 시, 위치와 거리 추가
        if (arr[nx][ny] >= 1 && arr[nx][ny] <= 6 && arr[nx][ny] < bSize) {
          edibleFish.push([nx, ny, dist + 1]);
        }
      }
    }
  }
  return edibleFish;
}

function getSecond(arr, N, babyShark) {
  let [bx, by, bSize] = babyShark;
  let cnt = 0;
  let eatCnt = 0;

  while (true) {
    let edibleFish = findFish(arr, N, [bx, by, bSize]);

    // 먹을 수 있는 생선이 없다면 종료
    if (edibleFish.length === 0) break;

    // 거리(오름차순)->위(오름차순)->아래(오름차순)
    edibleFish.sort((a, b) => {
      if (a[2] !== b[2]) {
        return a[2] - b[2];
      } else if (a[0] !== b[0]) {
        return a[0] - b[0];
      } else {
        return a[1] - b[1];
      }
    });

    // 차례대로 물고기를 먹으며 위치와 값 업데이트
    const [fx, fy, dist] = edibleFish[0];

    // 원래자리 0으로
    arr[bx][by] = 0;

    // 이동후의 자리도 0으로
    bx = fx;
    by = fy;
    arr[bx][by] = 0;

    // 값 업데이트
    cnt += dist;
    eatCnt++;

    // 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
    if (eatCnt === bSize) {
      bSize++;
      eatCnt = 0;
    }
  }

  return cnt;
}

const filePath = process.platform === "linux" ? "/dev/stdin" : "./16236.txt";
const input = require("fs")
  .readFileSync(filePath)
  .toString()
  .trim()
  .split("\n");

const N = parseInt(input[0]);
const arr = input.slice(1).map((l) => l.split(" ").map(Number));

let babyShark = [];

arr.forEach((row, rIdx) => {
  row.forEach((val, idx) => {
    if (val === 9) babyShark = [rIdx, idx, 2];
  });
});

const ans = getSecond(arr, N, babyShark);
console.log(ans);
