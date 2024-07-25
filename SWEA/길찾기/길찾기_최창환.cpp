#include <iostream>
#include <vector>
using namespace std;

int ans;
vector<vector<int> > v(100);

void init()
{
  int n, s, e;
  ans = 0;
  for(int i = 0; i < 100; ++i) {
    v[i].clear();
    v[i].shrink_to_fit();
  }
  cin >> n;
  while(n--) {
    cin >> s >> e;
    v[s].push_back(e);
  }
}

void run(int idx)
{
  if(idx == 99) {
    ans = 1;
    return;
  }
  for(int i = 0; i < v[idx].size(); ++i)
    run(v[idx][i]);
}

int main()
{
  int T = 10, i;
  while(T--) {
    cin >> i;
    init();
    run(0);
    cout << '#' << i << ' ' << ans << endl;
  }
}
