//六角填数
//毛济洲20182344050
#include<iostream>
#include<algorithm>
using namespace std;

int num[12];
bool used[13];

bool ok()
{
	int a1 = num[0] + num[2] + num[5] + num[7];	
	int a2 = num[0] + num[3] + num[6] + num[10];
	int a3 = num[7] + num[8] + num[9] + num[10];
	int a4 = num[1] + num[2] + num[3] + num[4];
	int a5 = num[1] + num[5] + num[8] + num[11];
	int a6 = num[4] + num[6] + num[9] + num[11];

	return a1 == a2 && a2 == a3 && a3 == a4 && a4 == a5 && a5 == a6;
}

void solve(int p)
{
	if(p == 11)
	{
		if(ok())
		cout << num[5] << endl;
		return;
	}
	for(int i = 2; i < 13; i++)
	{
		if(!used[i])
		{
			num[p] = i;
			used[i] = true;
			solve(p + 1);
			used[i] = false;
		}
	}
}

int main()
{

	
	num[0] = 1;
	num[1] = 8;
	num[11] = 3;
	
	used[1]  = true;
	used[3] = true;
	used[8] = true;
	
	solve(2);
}

