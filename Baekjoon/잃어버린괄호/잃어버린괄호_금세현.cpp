#include <bits/stdc++.h>
using namespace std;


int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    string s; cin>>s;
    int sum=0; int temp=0; bool flag=false;

    for(int i=0;i<=s.size();i++){
        if(s[i]== '+' || s[i]=='-' || i==s.size()){
            if(flag==false){
                sum+=temp;
                temp=0;
            }
            else{
                sum-=temp;
                temp=0;
            }
            if(s[i]=='-') flag=true;
        }
        else {
            temp*=10;
            temp+=s[i]-'0';
        }
    }
    cout<<sum<<'\n';
}
