function extractUrl(page) {
  // [^"] : "를 제외한 모든 문자
  // *s는 : 0회이상 반복되는 문자열
  const pattern = /<meta property="og:url" content="([^"]*)"/;
  const urlMatch = page.match(pattern);
  return urlMatch ? urlMatch[1] : null;
}
function extractLinks(page) {
  const pattern = /<a href="([^"]*)"/g;
  const links = [];
  let linkMatch;
  while ((linkMatch = pattern.exec(page)) !== null) {
    links.push(linkMatch[1]);
  }
  return links;
}

function calculateBasicScore(bodyText, pattern) {
  return (bodyText.match(pattern) || []).length;
}

function calculateLinkScores(pageData, pageLinks) {
  // Map 내부에서 계산
  for (const [pageUrl, links] of pageLinks.entries()) {
    const currentPageData = pageData.get(pageUrl);
    if (currentPageData && currentPageData.outLinksCount > 0) {
      const linkScore =
        currentPageData.basicScore / currentPageData.outLinksCount;
      for (const link of links) {
        const linkedPageData = pageData.get(link);
        if (linkedPageData) {
          linkedPageData.totalScore += linkScore;
        }
      }
    }
  }
}

function calculateFinalScores(pageData) {
  // Map 내부에서 계산
  for (const [url, data] of pageData.entries()) {
    data.totalScore += data.basicScore;
  }
}

function solution(word, pages) {
  const numOfPages = pages.length;
  const pageData = new Map();
  const pageLinks = new Map();

  // \b는 단어경계를 나타내서 단어 구분이 가능하게 함
  const wordPattern = new RegExp(`\\b${word}\\b`, "gi");

  for (let i = 0; i < numOfPages; i++) {
    const page = pages[i];

    // URL 추출(https://example.com 형태)
    const url = extractUrl(page);

    if (url) {
      // 연결된 a태그 추출(https://example.com 형태를 담은 리스트 추출)
      const links = extractLinks(page);
      pageLinks.set(url, links);

      // body 태그 내용 추출
      const bodyMatch = page.split("<body>")[1]?.split("</body>")[0];

      if (bodyMatch) {
        // 숫자와 특수문자 제거
        const cleanBodyText = bodyMatch.replace(/[^a-zA-Z\s]/g, " ");
        const basicScore = calculateBasicScore(cleanBodyText, wordPattern);

        pageData.set(url, {
          idx: i,
          basicScore,
          outLinksCount: links.length,
          totalScore: 0,
        });
      }
    }
  }

  calculateLinkScores(pageData, pageLinks);
  calculateFinalScores(pageData);

  // idx 업데이트
  let maxScore = -Infinity;
  let resultIdx = -1;
  for (const [url, data] of pageData.entries()) {
    if (
      data.totalScore > maxScore ||
      (data.totalScore === maxScore && data.idx < resultIdx)
    ) {
      maxScore = data.totalScore;
      resultIdx = data.idx;
    }
  }

  return resultIdx;
}
