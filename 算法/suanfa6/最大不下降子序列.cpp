//   Created by Visual Studio Code.
//   User: Harry Mao
//   Date: 2020/11/28
//   Time: 15:28
//   To change this template use File | Settings | File Templates.
#include <iostream>
using namespace std;

int sf(int a[], int n)
{
	int dp[n];
	for (int i = 0; i < n; i++)
	{
		dp[i] = 0;
		for (int j = 0; j < i; j++)
		{
			if (a[j] <= a[i] && dp[j] > dp[i])
			{
				dp[i] = dp[j];
			}
		}
		dp[i] = dp[i] + 1;
	}
	int maxL = dp[0];
	for (int i = 0; i < n; ++i)
	{
		if (dp[i] > maxL)
			maxL = dp[i];
	}
	cout << "最长不下降子序列长度为" << maxL;
}

int main()
{
	int a[] = {4, 6, 5, 4, 3};
	int n = sizeof(a) / sizeof(a[0]);
	sf(a, n);
}
