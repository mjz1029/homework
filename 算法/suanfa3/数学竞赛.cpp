#include <iostream>
using namespace std;
int main(int argc, char **argv){                            
                                //甲乙丙丁分别为a,b,c,d
    unsigned int a, b, c, d;    //a为1-4时
    for (a = 0; a <= 4; a++)
    {
        for (b = 0; b <= 4; b++)
        {
            for (c = 0; c <= 4; c++)
            {
                for (d = 0; d <= 4; d++)
                {
                    if (a + b + c + d == 10)
                    {           //a,b,c,d的值不能相同
                        if (a == b || a == c || a == d || b == c || b == d || c == d)
                            continue;
                        if (((c == 1) + (a == 3) == 1) && ((b == 1) + (d == 4) == 1) && ((d == 2) + (c == 3) == 1))
                        {
                            printf("a is %d\n", a);
                            printf("b is %d\n", b);
                            printf("c is %d\n", c);
                            printf("d is %d\n", d);
                        }
                    }
                }
            }
        }
    }
    return 0;
}
