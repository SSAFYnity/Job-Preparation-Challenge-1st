const fs = require('fs');

// Read the file content
const fileContent = fs.readFileSync('input.txt', 'utf-8').trim().split('\n');

// Get the first line and split it into [N, S]
const [N, S] = fileContent[0].split(' ').map(Number);

// Get the second line and split it into an array of numbers
const nums = fileContent[1].split(' ').map(Number);


if (nums.reduce((a, b) => a + b, 0) < S) {
    console.log(0);
} else if (Math.max(nums) >= S) {
    console.log(1);
} else {
    let ans = nums.length;
    let r = 0;
    let len = 0;
    let sum = 0;
    for (let l = 0; l < N; l++) {
        while (sum < S && r < N) {
            sum += nums[r];
            len++;
            r++;
        }
        if (sum >= S) {
            ans = Math.min(ans, len)
        }
        len--
        sum -= nums[l]
    }
    console.log(ans)
}

