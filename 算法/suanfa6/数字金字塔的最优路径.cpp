//   Created by Visual Studio Code.
//   User: Harry Mao
//   Date: 2020/11/28
//   Time: 15:13
//   To change this template use File | Settings | File Templates.
#include <iostream>
using namespace std;

int main()
{
	int n[5][5];
	int a[] = {3, 1, 5, 8, 4, 3, 2, 6, 7, 9, 6, 2, 3, 5, 1};
	int f = 0;
	for (int i = 0; i < 5; i++)
	{
		for (int j = 0; j <= i; j++)
		{
			n[i][j] = a[f];
			f++;
		}
	}

	int dp[5];
	dp[0] = n[0][0];
	for (int i = 1; i < 5; i++)
	{
		for (int j = i; j >= 0; j--)
		{
			if (j == i)
			{
				dp[i] = dp[i - 1] + n[i][j];
			}
			else if (j == 0)
			{
				dp[0] += n[i][0];
			}
			else
			{
				dp[j] = max(dp[j], dp[j - 1]) + n[i][j];
			}
		}
	}
	int m = dp[0];
	for (int i = 0; i < 5; i++)
	{
		if (m < dp[i])
		{
			m = dp[i];
		}
	}
	cout << m;
}
