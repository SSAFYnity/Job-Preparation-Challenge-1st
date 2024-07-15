let input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("");

let time = 0;

for (let i = 0; i < input.length; i++) {
  let asc = input[i].charCodeAt();
  if (65 <= asc && asc < 68) {
    time += 3;
  } else if (68 <= asc && asc < 71) {
    time += 4;
  } else if (71 <= asc && asc < 74) {
    time += 5;
  } else if (74 <= asc && asc < 77) {
    time += 6;
  } else if (77 <= asc && asc < 80) {
    time += 7;
  } else if (80 <= asc && asc < 84) {
    time += 8;
  } else if (84 <= asc && asc < 87) {
    time += 9;
  } else {
    time += 10;
  }
}

console.log(time);
