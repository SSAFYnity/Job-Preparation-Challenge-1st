function solution(friends, gifts) {
  const len = friends.length;

  const presentLog = Array(len)
    .fill()
    .map(() => Array(len).fill(0));
  const presentScore = new Map();

  friends.forEach((friend) => presentScore.set(friend, 0));

  for (const gift of gifts) {
    let [from, to] = gift.split(" ");
    presentLog[friends.indexOf(from)][friends.indexOf(to)] += 1;

    // 선물 지수 계산
    presentScore.set(from, presentScore.get(from) + 1);
    presentScore.set(to, presentScore.get(to) - 1);
  }

  const nextPresent = Array(len).fill(0);
  for (let i = 0; i < len; i++) {
    for (let j = i; j < len; j++) {
      if (i === j) continue;

      if (presentLog[i][j] > presentLog[j][i]) {
        nextPresent[i]++;
      } else if (presentLog[i][j] < presentLog[j][i]) {
        nextPresent[j]++;
      } else {
        // 같다면
        if (presentScore.get(friends[i]) > presentScore.get(friends[j])) {
          nextPresent[i]++;
        } else if (
          presentScore.get(friends[i]) < presentScore.get(friends[j])
        ) {
          nextPresent[j]++;
        }
      }
    }
  }

  return Math.max(...nextPresent);
}
