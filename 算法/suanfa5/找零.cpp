#include <iostream>
#include <algorithm>

	using namespace std;
	const int N = 7;
	int Count[N] = {3, 0, 2, 1, 0, 3, 5};	//钱币数量
	int Value[N] = {1, 2, 5, 10, 20, 50, 100};//钱币面值
	int solve(int money)
	{
		int num = 0;
		for (int i = N - 1; i >= 0; i--)
		{
			int c = min(money / Value[i], Count[i]);
			money = money - c * Value[i];
			num += c;
		}
		if (money > 0)
			num = -1;
		return num;
	}
	int main() 
{	int money;
	cin >> money;
	int res = solve(money);
	if (res != -1)
		cout << res << endl;
	else
		cout << "NO" << endl;
}