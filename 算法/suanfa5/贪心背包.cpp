#include <iostream>
using namespace std;
const int N = 4;
void knapsack(float M, float v[], float w[], float x[]);

int main()
{
    float M = 50;
    //背包所能容纳的重量
    float w[] = {0, 10, 30, 20, 5};
    //每种物品的重量
    float v[] = {0, 200, 400, 100, 10};
    //每种物品的价值
    float x[N + 1] = {0};
    //记录结果的数组
    knapsack(M, v, w, x);
    cout << "选择装下的物品比例：" << endl;
    for (int i = 1; i <= N; i++)
        cout << "[" << i << "] :" << x[i] << endl;
}

void knapsack(float M, float v[], float w[], float x[])
{
    int i;
    //物品整件被装下
    for (i = 1; i <= N; i++)
    {
        if (w[i] > M)
            break;
        x[i] = 1;
        M -= w[i];
    }
    //物品部分被装下
    if (i <= N)
        x[i] = M / w[i];
}