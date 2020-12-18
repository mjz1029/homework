
#include<iostream>

using namespace std;
int main()
{
	int m,n,a[10010],f[10010][2];
	cin>>m;
	for(int i=1;i<=m;i++)
		cin>>a[i];
	memset(f,0,sizeof(f));
	f[1][0]=a[1];//爬到第一层
	f[1][1]=0;//跳到第一层
	for(int i=2;i<=m;i++)
	{
		f[i][0]=min(f[i-1][0],f[i-1][1])+a[i];
		f[i][1]=min(f[i-1][0],f[i-2][0]);
	}
	cout<<min(f[m][0],f[m][1])<<endl;
}
