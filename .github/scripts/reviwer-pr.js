const core = require('@actions/core');
const github = require('@actions/github');

function getRandomElements(array, count) {
    // 배열을 복사하여 원본 배열이 변하지 않도록 함
    let shuffled = array.slice();

    // Fisher-Yates 알고리즘으로 배열을 무작위로 섞음
    for (let i = shuffled.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
    }

    // 앞에서부터 count 개수만큼 요소를 선택
    return shuffled.slice(0, count);
}

async function run() {
  try {
    const token = process.env.GH_TOKEN;
    const javaReviwer = process.env.REVIWER_JAVA;
    const pythonReviwer = process.env.REVIWER_PYTHON;
    const javaScriptReviwer = process.env.REVIWER_JAVASCRIPT;
    const cppReviwer = process.env.REVIWER_CPP;
    const author = process.env.ACTOR;

    console.log(author);
    
    if (!token) {
      throw new Error('GitHub token is not provided');
    }

    const octokit = github.getOctokit(token);
    const context = github.context;
    const { owner, repo } = context.repo;
    const pull_number = context.issue.number;

    const { data: files } = await octokit.rest.pulls.listFiles({
      owner,
      repo,
      pull_number
    });

    let reviewers = [];
    files.forEach(file => {
      // 확장자에 따른 reviwer 추가
        if (file.filename.endsWith(".java")) {
          javaReviwer.split(",").forEach(e => { reviewers.push(e) });
        } else if (file.filename.endsWith(".py")) {
          pythonReviwer.split(",").forEach(e => { reviewers.push(e) });
        } else if (file.filename.endsWith(".js")) {
          javaScriptReviwer.split(",").forEach(e => { reviewers.push(e) });
        } else if (file.filename.endsWith(".cpp")) {
          cppReviwer.split(",").forEach( e => { reviewers.push(e) });
        }
    });

    reviewers = reviewers.filter(reviewer => reviewer !== author);
    if (reviewers.length > 14) reviewers = getRandomElements(reviewers, 14);

    if (reviewers.length > 0) {
      await octokit.rest.pulls.requestReviewers({
        owner,
        repo,
        pull_number,
        reviewers
      });
    }
    
  } catch (error) {
    core.setFailed(error.message);
  }
}

run();