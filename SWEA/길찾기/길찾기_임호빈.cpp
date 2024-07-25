#include <stdio.h>
#include <stdlib.h>
#include<string.h>

using namespace std;

int arr[100][2] = { 0, };

int dfs(int n,int way){
    if (arr[n][way] == 99)return 1;
    if (arr[n][way] == 0) return 0;

    if (dfs(arr[n][way], 0)) return 1;//첫번째 배열의 순서쌍
    return dfs(arr[n][way], 1);//두번째 배열의 순서쌍
}


int main(){

    for (int t = 1; t <= 10; t++)
    {
        int test=0;
        int n=0;
        scanf("%d %d",&test,&n);
        for (int i = 0; i < n; i++) {
            int a, b;
            scanf("%d %d",&a,&b);
            if (arr[a][0] != 0) {
                arr[a][1] = b;
                continue;
            }
            arr[a][0] = b;
        }
        printf("#%d %d\n",t,dfs(0, 0));
        memset(arr, 0, sizeof(arr));
    }
    
    return 0;
}