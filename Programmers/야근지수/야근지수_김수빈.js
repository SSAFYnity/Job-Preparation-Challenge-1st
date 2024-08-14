class MaxHeap {
  constructor() {
    this.heap = []
  }

  size() {
    return this.heap.length
  }

  getMax() {
    return this.heap[0] ? this.heap[0] : null
  }

  swap(a, b) {
    [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]]

  }

  insert(value) {
    this.heap.push(value)
    let index = this.heap.length - 1
    while (index > 0) {
      let parentIndex = Math.floor((index - 1) / 2)
      if (this.heap[parentIndex] < this.heap[index]) {
        this.swap(parentIndex, index)
        index = parentIndex
      } else {
        break
      }
    }
  }

  extract() {
    if (this.heap.length === 0) return null
    if (this.heap.length === 1) return this.heap.pop()
    
      const maxValue = this.heap[0]
      this.heap[0] = this.heap.pop()
      this.heapifyDown(0)
      return maxValue
  }

  heapifyDown(index) {
    let parentIndex = index
    const leftChildIndex  = 2 * index + 1
    const rightChildIndex = 2 * index + 2

    if (leftChildIndex < this.heap.length && this.heap[leftChildIndex] > this.heap[parentIndex]) { 
      parentIndex = leftChildIndex
    }

    if (rightChildIndex < this.heap.length && this.heap[rightChildIndex] > this.heap[parentIndex]) {
      parentIndex = rightChildIndex
    }
    
    if (parentIndex !== index) {
      this.swap(index, parentIndex)
      this.heapifyDown(parentIndex)
    }
  }
}



function solution(n, works) {
  const heap = new MaxHeap()
  for (const work of works) {
    heap.insert(work)
  }

  for (let i = 0; i < n; i++) {
    let maxValue = heap.extract()
    if (maxValue > 0) {
      heap.insert(maxValue - 1)
    } else {
      break
    }
  }

  let overnightWork = 0
  heap.heap.forEach(h => {
    overnightWork += h ** 2
  })

  return overnightWork
}

console.log(solution(4, [4, 3, 3]))