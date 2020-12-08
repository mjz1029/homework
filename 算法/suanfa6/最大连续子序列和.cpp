//   Created by Visual Studio Code.
//   User: Harry Mao
//   Date: 2020/11/28
//   Time: 15:38
//   To change this template use File | Settings | File Templates.
#include <iostream>
using namespace std;

int main()
{
	int a[9] = {6, -1, 3, -4, -6, 9, 2, -2, 5};

	int n = 9;
	int m = a[0];
	int sum = a[0];
	for (int i = 1; i < n; i++)
	{
		sum = max(sum + a[i], a[i]);
		if (sum >= m)
		{
			m = sum;
		}
	}
	cout << m;
}
