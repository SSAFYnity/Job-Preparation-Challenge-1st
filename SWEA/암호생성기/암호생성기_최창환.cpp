#include <iostream>
#include <queue>
using namespace std;

queue<int> q;

void init()
{
  int num;
  for(int i = 0; i < 8; ++i) {
    cin >> num;
    q.push(num);
  }
}

void run()
{
  int cnt = 0;
  while(true) {
    if(++cnt == 6) cnt = 1;
    if(q.front() - cnt > 0) {
      q.push(q.front() - cnt);
      q.pop();
    } else {
      q.push(0);
      q.pop();
      break;
    }
  }
}

void print()
{
  for(; !q.empty(); q.pop())
    cout << q.front() << ' ';
  cout << endl;
}

int main()
{
  int i;
  while(cin >> i) {
    init();
    run();
    cout << '#' << i << ' ';
    print();
  }
}
