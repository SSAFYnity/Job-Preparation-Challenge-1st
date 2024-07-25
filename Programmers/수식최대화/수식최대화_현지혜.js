function solution(expression) {
    let answer = 0; // 최대값은 음수인 경우에도 절대값을 붙이니까 최소인 0으로 대입.
    const combis = [ // 나올 수 있는 연산 우선순위 (총 6개의 경우의 수 존재)
        ["+", "*", "-"],
        ["+", "-", "*"],
        ["*", "+", "-"],
        ["*", "-", "+"],
        ["-", "*", "+"],
        ["-", "+", "*"],
    ];
    function calc(a,b,oper){ // 연산 함수
        if (oper === "+") return a + b;
        if (oper === "-") return a - b;
        if (oper === "*") return a * b;
    }
    combis.forEach((combi)=>{
        const nums = expression.match(/[0-9]+/g).map(Number); // 피연산자 담기
        const opers = expression.match(/[|*|+|-]/g); // 연산자 담기
        combi.forEach((i)=>{
            let idx = opers.indexOf(i); // 우선순위 차례대로 연산자를 opers에서 찾기
            while (idx !== -1){ // indexOf값으로 찾는 값이 없을 경우 -1 리턴
                nums[idx] = calc(nums[idx],nums[idx+1],i) // 계산한 값 다시 집어넣기
                nums.splice(idx+1,1); // 계산한 요소 뒤에 값 하나 잘라내기(위에서 계산됬으니까)
                opers.splice(idx,1); // 계산한 연산자 1개 삭제
                idx = opers.indexOf(i); // 동일한 연산자가 더 존재할 수 있으니까
            }
        })
        if (answer < Math.abs(nums[0])) answer = Math.abs(nums[0]); // 6개 값 중 최대값 찾기
    })
 return answer;   
}
