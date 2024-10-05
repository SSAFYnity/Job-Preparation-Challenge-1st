function solution(n, works) {
    
    let workCount = new Map;

    for (let work of works) {
        workCount.set(work, (workCount.get(work) || 0) + 1);
    }
    
    let i = Math.max(...works);

    while (n) {

        while (!workCount.get(i) && i) {
            i--;
        }

        if (i === 0) {
            break;
        }

        i = workCount.get(i) ? i : i--;
        workCount.set(i, (workCount.get(i) || 0) - 1);
        workCount.set(i - 1, (workCount.get(i - 1) || 0) + 1);
        n--;
    }

    let answer = 0;

    for (let [key, val] of workCount) {
        answer += key ** 2 * val;
    }

    return answer;
}
