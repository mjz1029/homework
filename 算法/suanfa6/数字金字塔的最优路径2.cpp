//   Created by Visual Studio Code.
//   User: Harry Mao
//   Date: 2020/11/28
//   Time: 15:20
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

	int dp[5][5];
	for (int i = 0; i < 5; i++)
	{
		dp[4][i] = n[4][i];
	}
	for (int i = 3; i >= 0; i--)
	{
		for (int j = i; j >= 0; j--)
		{
			dp[i][j] = max(dp[i + 1][j], dp[i + 1][j + 1]) + n[i][j];
		}
	}
	cout << dp[0][0];
}
