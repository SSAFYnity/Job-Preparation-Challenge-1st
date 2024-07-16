function calculate(a, b, oper) {
  let num1 = parseInt(a);
  let num2 = parseInt(b);

  if (oper === "+") {
    return num1 + num2;
  } else if (oper === "-") {
    return num1 - num2;
  } else if (oper === "*") {
    return num1 * num2;
  }
}

function permutate(arr) {
  const result = [];

  function dfs(currentArr, existedArr) {
    if (existedArr.length === 0) {
      result.push([...currentArr]);
      return;
    }
    for (let i = 0; i < existedArr.length; i++) {
      const current = [...currentArr, existedArr[i]];
      const modified = existedArr.filter((_, idx) => idx !== i);
      dfs(current, modified);
    }
  }
  dfs([], arr);
  return result;
}

// 문자열이 주어졌을 때, 우승시 받을 수 있는 가장 큰 금액(절댓값)
// +, -, *
function solution(expression) {
  let answer = 0;
  let operator = ["+", "-", "*"];
  // 정규식으로 operation 분리
  const splitedArr = expression.split(/([+\-*/])/);
  const permutations = permutate(operator);

  // permutations순서대로 계산
  for (let i = 0; i < permutations.length; i++) {
    let temp = [...splitedArr];
		// temp를 업데이트해감
    permutations[i].forEach((oper) => {
      while (temp.includes(oper)) {
        const idx = temp.indexOf(oper);
				// 계산 수행
        let result = calculate(temp[idx - 1], temp[idx + 1], oper);
        temp.splice(idx - 1, 3, result.toString());
      }
    });
		// 최대값 반영
    let newVal = Math.abs(parseInt(temp[0]));
    answer = Math.max(answer, newVal);
  }
  return answer;
}
