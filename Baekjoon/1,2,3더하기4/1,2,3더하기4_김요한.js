const fs = require('fs');
const input = fs.readFileSync('input.txt', 'utf-8').trim().split('\n').map(Number);

// recurrence relation:
// dp(n) = dp(n - 3) + math.floor(n / 2) + 1

function dp(n, memo) {

    if (n < 0) {
        return 0;
    }

    if (memo.has(n)) {
        return memo.get(n);
    }

    memo.set(
        n,
        dp(n - 3, memo) + Math.floor(n / 2) + 1
    );

    return memo.get(n);

};

let memo = new Map();
memo.set(0, 1);
memo.set(1, 1);
memo.set(2, 2);
memo.set(3, 3);
memo.set(4, 4);

const N = input.shift()

for (let i = 0; i < N; i++) {
    console.log(dp(input[i], memo));
}


