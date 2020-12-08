//带分数
//毛济洲20182344050
#include <iostream>
#include <algorithm>
using namespace std;

int a[9] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
int ans, t[3], n;

void dfs(int cur, int cnt)
{
	if (cnt > 1)
	{
		int sum = 0;
		for (int i = cur; i < 9; i++)
			sum = sum * 10 + a[i];
		t[cnt] = sum;
		if (t[1] % t[2] == 0 && t[0] + t[1] / t[2] == n)
			ans++;
		return;
	}
	int dd = 6 + cnt;
	for (int i = cur; i <= dd; i++)
	{
		t[cnt] = 0;
		int sum = 0;
		for (int j = cur; j <= i; j++)
		{
			sum = sum * 10 + a[j];
		}
		t[cnt] = sum;
		dfs(i + 1, cnt + 1);
	}
}

int main()
{
	while (~scanf("%d", &n))
	{
		ans = 0;
		sort(a, a + 9);
		do
		{
			dfs(0, 0);
		} while (next_permutation(a, a + 9));
		printf("%d\n", ans);
	}
}
