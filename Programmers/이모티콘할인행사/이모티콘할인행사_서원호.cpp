#include <string>
#include <vector>
using namespace std;

int emoSale[7];
int discounts[4] = {10, 20, 30, 40};
int emoTotal;
int maxPlus = 0;
int maxPrice = 0;

void buy(vector<vector<int>> &users, vector<int> &emos) {
    int totalPrice = 0;
    int totalPlus = 0;
    for (vector<int> user : users) {
        int price = 0;
        for (int i = 0; i < emoTotal; i++) {
            if (emoSale[i] >= user[0]) {
                price += emos[i] * (100 - emoSale[i]) / 100;
            }
            if (price >= user[1]) {
                totalPlus++;
                price = 0;
                break;
            }
        }
        totalPrice += price;
    }
    
    if (totalPlus < maxPlus) {
        return;
    }
    
    if (totalPlus == maxPlus && totalPrice <= maxPrice) {
        return;
    }
    maxPlus = totalPlus;
    maxPrice = totalPrice;
}

void search(vector<vector<int>> &users, vector<int> &emos, int depth, int start) {
    if (depth == emoTotal) {
        buy(users, emos);
        return;
    }
    
    for (int i = start; i < emoTotal; i++) {
        for (int j = 0; j < 4; j++) {
            emoSale[i] = discounts[j];
            search(users, emos, depth + 1, i + 1);
        }
    }
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
    emoTotal = emoticons.size();
    search(users, emoticons, 0, 0);
    
    return vector<int> {maxPlus, maxPrice};
}