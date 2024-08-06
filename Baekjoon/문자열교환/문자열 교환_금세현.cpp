#include <bits/stdc++.h>
using namespace std;

#define INF 0x3f3f3f3f;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);

    int ans=INF;
    string s; cin>>s;
    int a_cnt=0;
    for(int i=0;i<s.size();i++) {
        if(s[i]=='a') a_cnt++;
    }

    int idx=0;

    while (idx<s.size()) {
        int cnt=0;
        if(idx+a_cnt>s.size()) {
            for (int i=idx;i<s.size();i++) {
                if(s[i]=='b') cnt++;
            }
            for (int i=0;i<idx+a_cnt-s.size();i++) {
                if(s[i]=='b') cnt++;
            }
        }else {
            for (int i=idx;i<idx+a_cnt;i++) {
                if(s[i]=='b') cnt++;
            }
        }
        idx++;
        ans=min(ans,cnt);
    }

    cout<<ans<<'\n';
}
