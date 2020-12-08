#include <iostream>

using namespace std;
int main(int argc, char *argv[])
{
	int l, m, r; //left,middle,right
	for (l = 0; l <= 1; l++)
	{
		for (m = 0; m <= 1; m++)
		{
			for (r = 0; r <= 1; r++)
			{
				if ((l == 0 + m == 0 + l == 1) == 1 && (l + m + r) == 1)
				{
					cout << "left:" << l << " middle:" << m << " right:" << r << endl;
				}
			}
		}
	}
	return 0;
}