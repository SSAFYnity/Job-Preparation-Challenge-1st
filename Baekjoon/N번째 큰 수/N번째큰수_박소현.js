const readline = require("readline");

class MinHeap {
  constructor() {
    // 계산을 위해 0번 인덱스는 빈 채로 둔다
    this.heap = [null];
  }

  size() {
    return this.heap.length - 1;
  }

  heapPush(val) {
    this.heap.push(val);
    let currentIdx = this.heap.length - 1;
    let parentIdx = Math.floor(currentIdx / 2);

    while (parentIdx > 0 && this.heap[parentIdx] > val) {
      [this.heap[parentIdx], this.heap[currentIdx]] = [
        this.heap[currentIdx],
        this.heap[parentIdx],
      ];
      currentIdx = parentIdx;
      parentIdx = Math.floor(currentIdx / 2);
    }
  }

  heapPop() {
    // null과 실제 노드값이 하나 남았을때는 바로 pop한것을 return
    if (this.size() === 2) return this.heap.pop();

    const returnVal = this.heap[1];
    this.heap[1] = this.heap.pop();

    let currentIdx = 1;
    let leftIdx = 2 * currentIdx;
    let rightIdx = 2 * currentIdx + 1;

    while (true) {
      // 왼쪽 오른쪽 자식노드가 존재하는지 확인하고
      const leftValid = leftIdx < this.heap.length;
      const rightValid = rightIdx < this.heap.length;

      if (!leftValid && !rightValid) break;

      // 더 작은 값을 타겟으로 함
      const swapIdx =
        rightValid && (!leftValid || this.heap[rightIdx] < this.heap[leftIdx])
          ? rightIdx
          : leftIdx;

      if (this.heap[currentIdx] > this.heap[swapIdx]) {
        [this.heap[currentIdx], this.heap[swapIdx]] = [
          this.heap[swapIdx],
          this.heap[currentIdx],
        ];
        currentIdx = swapIdx;
        leftIdx = 2 * currentIdx;
        rightIdx = 2 * currentIdx + 1;
      } else {
        break;
      }
    }

    return returnVal;
  }
}

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const minHeap = new MinHeap();
let N;
let rowCnt = 0;

rl.on("line", function (line) {
  if (!N) {
    N = parseInt(line);
  } else {
    let row = line.split(" ").map(Number);
    // 작동코드
    for (let j = 0; j < N; j++) {
      minHeap.heapPush(row[j]);
      if (minHeap.size() > N) {
        minHeap.heapPop();
      }
    }
    rowCnt++;
    if (rowCnt === N) {
      rl.close();
    }
  }
}).on("close", function () {
  // N번째로 큰 값 추출
  const ans = minHeap.heapPop();
  console.log(ans);
});
