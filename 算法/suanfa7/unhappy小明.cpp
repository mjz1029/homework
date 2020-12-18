#include<iostream>
using namespace std;
typedef long long int ll;
ll w[1005];
ll dp[1005][1005];
ll n,p,t;
const ll inf=0x3f3f3f;
int main(){
    cin>>n>>p>>t;
    for(int i=1;i<=n;i++)cin>>w[i];
    for(int i=n;i>=n-p&&i>=0;i--)dp[i][1]=w[i];///目标即是达到跳数为1时达到这些点
    for(int i=n;i>=0;i--){
        for(int j=2;j<=t;j++){///最后到达剩余1次跳数的格子
            dp[i][j]=-inf;///先置为无法到达(跳数过多or跳数过少）
            for(int k=1;k<=p&&i+k<=n;k++){
                dp[i][j]=max(dp[i][j],dp[i+k][j-1]);
            }
            if(dp[i][j]!=-inf)dp[i][j]+=w[i];///如果可以到达，加上本身的格子
        }
    }
    cout<<dp[0][t]<<endl;///从0位置花费t次到达另一边
    return 0;
}