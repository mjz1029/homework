#include <iostream>
using namespace std;

int main()
{
    int p, e, i, d;         //p, e, i 分别表示体力、情感和智力高峰出现的时间;d是给定的时间
    int n = 1;
    while (cin >> p >> e >> i >> d && p != -1 && e != -1 && i != -1 && d != -1)
    {
        int j;
        p = p % 23;
        e = e % 28;
        i = i % 33;
        for (j = p + 23; j >= 0; j = j + 23)
        {
            if ((j - e) % 28 == 0)
            {
                if ((j - i) % 33 == 0)
                {
                    break;
                }
            }
        }
        cout << "第" << n << "个："
             << "下一次三个高峰同天的时间：" << j - d << "天" << endl;
        n++;
    }
    return 0;
}