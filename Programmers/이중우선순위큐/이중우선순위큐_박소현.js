// 최댓값, 최솟값
function solution(operations) {
  let answer = [0, 0];
  let q = [];

  for (const operation of operations) {
    let [sign, tempNum] = operation.split(" ");
    let num = parseInt(tempNum);

    if (sign === "I") {
      q.push(num);
    } else if (sign === "D" && q.length > 0) {
      if (num === 1) {
        let maxVal = Math.max(...q);
        let idx = q.indexOf(maxVal);
        q.splice(idx, 1);
      } else if (num === -1) {
        let minVal = Math.min(...q);
        let idx = q.indexOf(minVal);
        q.splice(idx, 1);
      }
    }
  }
  if (q.length > 0) {
    answer = [Math.max(...q), Math.min(...q)];
  }
  return answer;
}
