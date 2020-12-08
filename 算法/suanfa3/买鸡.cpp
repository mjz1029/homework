#include <iostream>

using namespace std;
int main(int argc, char *argv[])
{
	int a, b, c;
	for (int k = 1; k <= 3; k++)
	{
		a = 4 * k;
		b = 25 - 7 * k;
		c = 75 + 3 * k;
		printf("当公鸡的数量为--%d\t母鸡的数量是---%d\t小鸡的数量为--%d\n", a, b, c);
	}
	return 0;
}