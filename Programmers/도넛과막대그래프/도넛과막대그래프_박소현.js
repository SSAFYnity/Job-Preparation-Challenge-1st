// [생성한 정점의 번호, 도넛, 막대, 8자모양 그래프 수] : 들어오는 간선 갯수와 나가는 간선 갯수로 판단

// 생성한 노드 판별 : in = 0, out >= 2
// 막대 : out = 0
// 8자 : in >= 2, out >= 2
// 도넛 : 생성 노드의 out 개수 - (막대 + 8자)

function solution(edges) {
  const answer = [0, 0, 0, 0];

  const counter = {};

  // 간선 정보(in, out) 저장
  edges.forEach(([a, b]) => {
    // out
    if (!counter[a]) {
      counter[a] = [0, 1];
    } else {
      counter[a][1] += 1;
    }
    // in
    if (!counter[b]) {
      counter[b] = [1, 0];
    } else {
      counter[b][0] += 1;
    }
  });

  for (const node in counter) {
    let [i, o] = counter[node];

    if (i === 0 && o >= 2) {
      answer[0] = +node;
    } else if (o === 0) {
      answer[2]++;
    } else if (i >= 2 && o >= 2) {
      answer[3]++;
    }
  }

  answer[1] = counter[answer[0]][1] - (answer[2] + answer[3]);

  return answer;
}
