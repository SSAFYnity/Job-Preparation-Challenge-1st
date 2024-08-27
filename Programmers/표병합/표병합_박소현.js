const table = Array(50)
  .fill()
  .map(() => Array(50).fill(""));

function printTable(r, c) {
  let ans = table[r - 1][c - 1] !== "" ? table[r - 1][c - 1] : "EMPTY";
  return ans;
}

function unmergeTable(r, c, mergedCells) {}

function mergeTable(r1, c1, r2, c2) {
  if (r1 === r2 && c1 === c2) return;
}

function updateTableAll(val1, val2) {
  table.forEach((row, i) => {
    row.forEach((val, j) => {
      if (val === val1) {
        table[i][j] = val2;
      }
    });
  });
}

function updateTable(r, c, val) {
  table[r - 1][c - 1] = val;
}

function solution(commands) {
  let answer = [];
  let mergedCells = [];

  for (let command of commands) {
    let com = command.split(" ");
    if (com[0] === "UPDATE" && com.length === 4) {
      updateTable(Number(com[1]), Number(com[2]), com[3]);
    } else if (com[0] === "UPDATE" && com.length === 3) {
      updateTableAll(com[1], com[2]);
    } else if (com[0] === "MERGE") {
      let merged = mergeTable(
        Number(com[1]),
        Number(com[2]),
        Number(com[3]),
        Number(com[4])
      );
      mergedCells[Number(com[1])];
    } else if (com[0] === "UNMERGE") {
      unmergeTable(Number(com[1]), Number(com[2]), mergedCells);
    } else if (com[0] === "PRINT") {
      let temp = printTable(Number(com[1]), Number(com[2]));
      answer.push(temp);
    }
  }

  return answer;
}
