// 교환 횟수
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./1522.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const arr = input[0];
const length = arr.length;

const aCnt = arr.split("").filter((char) => char === "a").length;
let minCnt = Infinity;

// a밖에 없다면 바로 반영
if (aCnt === length) {
  console.log(0);
  process.exit(0);
} else {
  // 슬라이딩 윈도우로 a길이 만큼의 범위안에 b가 있는지 찾아서 카운트
  for (let i = 0; i < length; i++) {
    let bCnt = 0;

    for (let j = i; j < i + aCnt; j++) {
      if (arr[j % length] === "b") {
        bCnt++;
      }
    }
    // 최소를 반환
    minCnt = Math.min(minCnt, bCnt);
  }

  console.log(minCnt);
}
