#include <iostream>
using namespace std;
int dp[100][100];
int m[100][100];

int main()
{
	int n, k, a;
	cin >> n >> k >> a;
	if (k == 1)
	{ //如果这个数分成一段，就直接输出这个数。
		cout << a;
		return 0;
	}
	int b = 1, q = 1;
	for (int i = n; i >= 1; i--)
	{ //
		int p = 10;
		b = a / q;
		q *= 10;
		//        cout << b << endl;
		for (int j = i; j >= 1; j--)
		{ //从第几位到第几位每段数字是多少全记录下来
			m[j][i] = b % p;
			p *= 10;
			//            cout << m[j][i] << " ";
		}
	}
	//   dp[1][1] = a;
	for (int i = 1; i <= n; i++)
	{ //枚举前n个数字
		for (int j = 1; j <= i; j++)
		{ //枚举乘号的个数//分成多少段
			if (j == 1)
			{
				dp[i][j] = m[1][i]; //前i个分成j段
				continue;
			}
			for (int nn = 1; nn < i; nn++) //枚举乘号的位置
				dp[i][j] = max(dp[i][j], dp[nn][j - 1] * m[nn + 1][i]);
		}
	}
	cout << dp[n][k];
	return 0;
}