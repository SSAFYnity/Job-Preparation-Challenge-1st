#include <string>
#include <vector>
#include <iostream>

using namespace std;

int heal(int hp, int amount, int max);

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int hp = health, turn = attacks[attacks.size() - 1][0] + 1, idx = 0, bandTime = 0;

    for (int i = 0; i < turn; i++) {
        // attack turn
        if (attacks[idx][0] == i) {
            hp -= attacks[idx++][1];
            if (hp <= 0) return -1;
            bandTime = 0;
            continue;
        }

        // heal turn
        hp = heal(hp, bandage[1], health);
        if (++bandTime == bandage[0]) {
            hp = heal(hp, bandage[2], health);
            bandTime = 0;
        }
    }

    return hp;
}

int heal(int hp, int amount, int max) {
    hp += amount;
    if (hp > max) hp = max;
    return hp;
}