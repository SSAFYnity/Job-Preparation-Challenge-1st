#include<iostream>
using namespace std;
int n;
void init() {
	cin >> n;
}
void solved() {
	if ((n - 1) % 4 == 0 || (n - 3) % 4 == 0) {
		cout << "SK";
	}
	else {
		cout << "CY";
	}
}
int main() {
	init();
	solved();
}