#include <iostream>
#include <queue>
using namespace std;

int n;
priority_queue<int, vector<int>, greater<> > q;

void init()
{
  cin >> n;
}

void run()
{
  int nn = n * n, num;
  while(nn--) {
    cin >> num;
    q.push(num);
    if(q.size() > n) q.pop();
  }
}

int main()
{
  ios::sync_with_stdio(false); cin.tie(nullptr);
  init();
  run();
  cout << q.top();
}
