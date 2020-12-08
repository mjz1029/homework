#include <iostream>

using namespace std;
int main(int argc, char *argv[])
{
	int n5, n1, n05;
	int count = 0;
	for (n5 = 1; n5 < 20; n5++)
	{
		for (n1 = 1; n1 < (100 - n5); n1++)
		{
			n05 = 100 - n5 - n1;
			if ((n5 * 10 + n1 * 2 + n05) == 200)
			{
				count++;
				printf("第%d种:\t%d张5元\t%d张一元\t%d张五角\n", count, n5, n1, n05);
			}
		}
	}
	return 0;
}