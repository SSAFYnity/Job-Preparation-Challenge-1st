// 정렬하여 푸시 팝
class DualPriorityQueue {
  constructor() {
    this.heap = []
  }

  insert(value) {
    this.heap.push(value)
    this.heap.sort((a, b) => a - b)
  }

  delMax() {
    if (this.heap.length > 0) {
      this.heap.pop()
    }
  }

  delMin() {
    if (this.heap.length > 0) {
      this.heap.shift()
    }
  }

  getResult() {
    if (this.heap.length === 0) {
      return [0, 0]
    }
    return [this.heap[this.heap.length - 1], this.heap[0]]
  }


}


function solution(operations) {
  const dualPriorityQueue = new DualPriorityQueue();

  operations.forEach(op => {
    const [command, value] = op.split(' ');
    if (command === 'I') {
      dualPriorityQueue.insert(Number(value));
    } else if (command === 'D') {
      if (value === '1') {
        dualPriorityQueue.delMax();
      } else if (value === '-1') {
        dualPriorityQueue.delMin();
      }
    }
  });

  return dualPriorityQueue.getResult();
}