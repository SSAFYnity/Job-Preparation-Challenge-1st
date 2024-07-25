function solution(picks, minerals) {

    const sortMinerals = (a, b) => {

        const aCnt = a[a.length - 1];
        const bCnt = b[b.length - 1];

        // desc
        if (aCnt['diamond'] !== bCnt['diamond']) {
            return bCnt['diamond'] - aCnt['diamond'];
        } else if (aCnt['iron'] !== bCnt['iron']) {
            return bCnt['iron'] - aCnt['iron'];
        } else {
            return bCnt['stone'] - aCnt['stone'];
        }

    };

    const fiveMinerals = (minerals, k) => {

        let res = [];
        let N = Math.floor((minerals.length + 4) / 5);
        let l = 0;
        let r = 5;

        while (N > 0) {

            const tmp = minerals.slice(l, r);

            let cnt = {
                'diamond': 0,
                'iron': 0,
                'stone': 0
            };

            tmp.forEach(min => {
                cnt[min]++
            });

            tmp.push(cnt);
            res.push(tmp);
            l += 5;
            r += 5;
            N--;
        }

        return res.slice(0, k);

    };

    var answer = 0;

    const chart = {
        'diamond-diamond': 1,
        'diamond-iron': 1,
        'diamond-stone': 1,
        'iron-diamond': 5,
        'iron-iron': 1,
        'iron-stone': 1,
        'stone-diamond': 25,
        'stone-iron': 5,
        'stone-stone': 1
    };

    const k = picks.reduce((acc, val) => acc + val, 0);
    let mineralChunks = fiveMinerals(minerals, k);
    mineralChunks.
    sort(sortMinerals).
    forEach(mineralChunkWithCnt => {
        let pick = '';
        if (picks[0] > 0) {
            pick = 'diamond';
            picks[0]--;
        } else if (picks[1] > 0) {
            pick = 'iron';
            picks[1]--;
        } else if (picks[2] > 0) {
            pick = 'stone';
            picks[2]--;
        } else {
            return answer
        }
        mineralChunkWithCnt.forEach(mineral => {
            if (typeof mineral === 'string') {
                answer += chart[`${pick}-${mineral}`]
            }
        });
    });

    return answer
}

