#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

static int RATE[4] = { 9, 8, 7, 6 }, U_RATE[41], DISCOUNT[7];
static int U_CNT, E_CNT;
static int* ANS, ** USERS, * EMOTS;
static void calc() {
    int cnt = 0, price = 0, p, i, j;
    for(i = 0; i < U_CNT; i++) {
        p = 0;
        for(j = 0; j < E_CNT; j++) {
            if(DISCOUNT[j] >= U_RATE[USERS[i][0]]) p += EMOTS[j] * RATE[DISCOUNT[j]];
        }
        p /= 10;
        if(p >= USERS[i][1]) cnt++;
        else price += p;
    }
    if(cnt < ANS[0]) return;
    if(cnt > ANS[0]) {
        ANS[0] = cnt, ANS[1] = price;
        return;
    }
    if(price >= ANS[1]) ANS[1] = price;
}
static void dfs(int c) {
    if(c == E_CNT) {
        calc();
    } else {
        for(int i = 0; i < 4; i++) {
            DISCOUNT[c] = i;
            dfs(c + 1);
        }
    }
}
int* solution(int** users, size_t users_rows, size_t users_cols, int emoticons[], size_t emoticons_len) {
    int* answer = (int*)malloc(2 * sizeof(int)), i;
    ANS = answer, USERS = users, EMOTS = emoticons, U_CNT = users_rows, E_CNT = emoticons_len;
    ANS[0] = 0, ANS[1] = 0;
    for(i = 1; i <= 40; i++) U_RATE[i] = (i - 1) / 10;

    dfs(0);

    return answer;
}
