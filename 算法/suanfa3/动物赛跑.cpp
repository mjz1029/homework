#include <iostream>
using namespace std;
int main()
{
    //小狗、小兔、小猫、小猴、小鹿分别定义为a,b,c,d,e
    unsigned int a, b, c, d, e;
    for (a = 1; a <= 5; a++)
    {
        for (b = 1; b <= 5; b++)
        {
            if (a == b)
                continue;
            for (c = 1; c <= 5; c++)
            {
                if (a == c || b == c)
                    continue;
                for (d = 1; d <= 5; d++)
                {
                    if (a == d || b == d || c == d)
                        continue;
                    e = 15 - a - b - c - d;
                    if ((d < c) && (e < a) && (b < d) && (b > a))
                    {
                        cout << "小狗: " << a << endl;
                        cout << "小兔: " << b << endl;
                        cout << "小猫: " << c << endl;
                        cout << "小猴: " << d << endl;
                        cout << "小鹿: " << e << endl;
                    }
                }
            }
        }
    }
    return 0;
}
