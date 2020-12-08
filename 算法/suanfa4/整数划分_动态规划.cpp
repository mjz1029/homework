#include <iostream>
 
#define MAXNUM 100            //最高次数
 
unsigned  long ww[MAXNUM*11][MAXNUM*11];
unsigned long dynamic_GetPartitionCount(int n, int max);
 
using namespace std;
 
int main(int argc, char **argv)
{
    int n;
    int m;
    unsigned long count;
	
	while(1)
	{
		cin>>n;
		cout<<dynamic_GetPartitionCount(n,n)<<endl;
	}
	
    return 0;
}
 
unsigned long dynamic_GetPartitionCount(int n, int max)
{
	for(int i=1;i<=n;i++)
		for(int j=1;j<=i;j++)
		{
			if(j==1|| i==1)
			{
				ww[i][j]=1;
			}
			else 
			{
				if(j==i)
					ww[i][j]=ww[i][j-1]+1;
				else if((i-j)<j)
					ww[i][j]=ww[i-j][i-j]+ww[i][j-1];
				else
					ww[i][j]=ww[i-j][j]+ww[i][j-1];
			}
		}
	return ww[n][max];
}