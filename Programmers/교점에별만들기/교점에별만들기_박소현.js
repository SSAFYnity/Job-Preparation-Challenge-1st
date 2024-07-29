function findIntersection(first, second) {
  let [a1, b1, c1] = first;
  let [a2, b2, c2] = second;

  // 참고사항에 따라 검증
  let isIntersect = a1 * b2 - a2 * b1;

  if (isIntersect === 0) {
    return [null, null];
  }

  let x = (b1 * c2 - b2 * c1) / isIntersect;
  let y = (a2 * c1 - a1 * c2) / isIntersect;

  // x와 y가 정수인지 확인
  if (Number.isInteger(x) && Number.isInteger(y)) {
    return [x, y];
  } else {
    return [null, null];
  }
}

function solution(line) {
  let checkSet = new Set();

  // 교차점을 찾아서
  for (let i = 0; i < line.length; i++) {
    for (let j = i + 1; j < line.length; j++) {
      let [x, y] = findIntersection(line[i], line[j]);
      if (x !== null && y !== null) {
        checkSet.add(`${x},${y}`);
      }
    }
  }

  // 교점의 정수 좌표를 배열로 변환
  let values = Array.from(checkSet).map((item) => item.split(",").map(Number));

  // 최대값, 최소값 계산
  let maxX = -Infinity;
  let maxY = -Infinity;
  let minX = Infinity;
  let minY = Infinity;

  values.forEach(([x, y]) => {
    maxX = Math.max(maxX, x);
    maxY = Math.max(maxY, y);
    minX = Math.min(minX, x);
    minY = Math.min(minY, y);
  });

  let width = maxX - minX + 1;
  let height = maxY - minY + 1;

  let arr = Array.from({ length: height }, () => Array(width).fill("."));
  values.forEach(([x, y]) => {
    arr[maxY - y][x - minX] = "*";
  });

  let answer = arr.map((row) => row.join(""));

  return answer;
}
