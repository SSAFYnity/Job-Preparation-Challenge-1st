function solution(operations) {
    let answer = [0,0]; // 최종 최댓값, 최솟값 넣을 배열
    let que = []; // 우선순위 큐
    for (let i = 0; i<operations.length; i++){
        if (operations[i].includes("I")){
            let [str,target] = operations[i].split(" ");
            que.push(parseInt(target)); // 숫자 큐에 넣기
        }else if (operations[i] === "D -1" ){
            if (que){
                que.splice(que.indexOf(Math.min(...que)), 1) // 최솟값 삭제
            }
        }else if (operations[i] === "D 1"){
            if (que){
                que.splice(que.indexOf(Math.max(...que)), 1) // 최댓값 삭제
            }
        }
    }
    if (que.length>0){
        answer =  [Math.max(...que), Math.min(...que)]; // que가 비지 않았을 때
    }else{
        answer = [0,0]; // que가 비었을 때
    }
    return answer;
}