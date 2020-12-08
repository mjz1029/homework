#include <iostream>
using namespace std;

const int ROW = 5;
const int COL = 5;
bool Find(int a[ROW][COL], int key)
{
    if (a && COL > 0 && ROW > 0)
    {
        int col = COL - 1;
        int row = 0;
        while (col >= 0 && row < ROW)
        {
            if (a[row][col] == key)
            {
                return true;
            }
            else if (a[row][col] > key)
            {
                col -= 1;
            }
            else
            {
                row += 1;
            }
        }
    }
    return false;
}

int main()
{
    int array[ROW][COL] = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
    int data = 88;
    cout << data << ":" << Find(array, data) << endl; //数组中没有的值
    data = 1;
    cout << data << ":" << Find(array, data) << endl; //最大值
    data = 30;
    cout << data << ":" << Find(array, data) << endl; //最小值
    data = 16;
    cout << data << ":" << Find(array, data) << endl; //中间值
    cout << 1 << ":" << Find(NULL, data) << endl;     //空指针
}