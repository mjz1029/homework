#include <iostream>
using namespace std;

int main()
{
    int n;
    int a, b, c, d;
    cin >> n;
    for (a = 2; a <= n; a++)
    {
        for (b = 2; b <= a - 1; b++)
        {
            for (c = b; c <= a - 1; c++)
            {
                for (d = c; d <= a - 1; d++)
                {
                    if (a * a * a == b * b * b + c * c * c + d * d * d)
                    {
                        // printf("Cube = %d, Triple = (%d, %d, %d)\n", a, b, c, d);
                        cout << "Cube = " << a << ", Triple = (" << b << "," << c << "," << d << ")" << endl;
                    }
                }
            }
        }
    }

    return 0;
}
