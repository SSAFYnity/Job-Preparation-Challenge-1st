function solution(n, m, x, y, queries) {
  const maxX = BigInt(n - 1);
  const maxY = BigInt(m - 1);

  let start = { x: BigInt(x), y: BigInt(y) };
  let end = { x: BigInt(x), y: BigInt(y) };

  // 도착점에서 역으로 추적하며 가능한 범위를 찾음
  for (let i = queries.length - 1; i >= 0; i--) {
    const [dir, dist] = queries[i];
    const distance = BigInt(dist);

    // 열 감소 -> 열 증가
    if (dir === 0) {
      if (start.y > 0n) {
        start.y += distance;
      }
      end.y += distance;

      if (end.y > maxY) {
        end.y = maxY;
      }

      // 열 증가 -> 열 감소
    } else if (dir === 1) {
      start.y -= distance;
      if (start.y < 0n) {
        start.y = 0n;
      }
      if (end.y < maxY) {
        end.y -= distance;
      }
      // 행 증가 -> 행 감소
    } else if (dir === 2) {
      if (start.x > 0n) {
        start.x += distance;
      }
      end.x += distance;
      if (end.x > maxX) {
        end.x = maxX;
      }
      // 행 감소 -> 행 증가
    } else if (dir === 3) {
      start.x -= distance;
      if (start.x < 0n) {
        start.x = 0n;
      }
      if (end.x < maxX) {
        end.x -= distance;
      }
    }

    if (start.x < 0n || start.y > maxY || end.x < 0n || end.y > maxY) {
      return 0;
    }
  }

  let answer = (end.x - start.x + 1n) * (end.y - start.y + 1n);

  return answer;
}
