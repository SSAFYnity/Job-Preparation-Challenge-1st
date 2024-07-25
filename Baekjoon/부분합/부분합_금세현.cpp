#include <bits/stdc++.h>
using namespace std;

typedef pair<int,int> pii;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    int n,s; cin>>n>>s;
    vector<int> v;
    for (int i = 0; i < n; ++i) {
        int a; cin>>a;
        v.push_back(a);
    }

    int res=n+1;
    int start=0; int end=0;
    int sum=v[0];
    while (start<=end && end<n){
        if(sum<s){
            sum += v[++end];

        }else{
            res=min(res,end-start+1);
            sum -= v[start++];

        }
    }
    if(res==n+1) cout<<0<<'\n';
    else cout<<res<<'\n';
}
