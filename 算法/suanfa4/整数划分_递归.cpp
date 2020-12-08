#include <stdio.h>
 
#define MAXNUM 100            //最高次数
 
//递归法求解整数划分
unsigned long GetPartitionCount(int n, int max)
{
    if(n == 1 || max == 1)
    {
        return 1;
    }
    if(n < max)
    {
        return GetPartitionCount(n, n);
    }
    if(n == max)
    {
        return 1 + GetPartitionCount(n, n - 1);
    }
    else
    {
        return GetPartitionCount(n - max, max) + GetPartitionCount(n, max - 1);
    }
}
 
 
int main(int argc, char **argv)
{
    int n;
    int m;
    unsigned long num;
    while(1)
    {
        scanf("%d", &n);
        if(n<=0)
            return 0;
        m=n;
        num = GetPartitionCount(n, m);
        printf("%d\n",num);
    }
    return 0;
}