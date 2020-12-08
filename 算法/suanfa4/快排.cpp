#include <iostream>
using namespace std;
void QS(int a[], int, int);

int main()
{
    int array[] = {45, 65, 145, 44, 63, 51, 7, 524, 342, 70}, k;
    int len = sizeof(array) / sizeof(int);
    cout << "原数据:" << endl;
    for (k = 0; k < len; k++)
        cout << array[k] << "\t";
    cout << "\n"
         << endl;
    QS(array, 0, len - 1);
    cout << "排序后:" << endl;
    for (k = 0; k < len; k++)
        cout << array[k] << "\t";
    cout << "\n"
         << endl;
    return 0;
}
void QS(int s[], int l, int r)
{
    if (l < r)
    {
        int i = l, j = r, x = s[l];
        while (i < j)
        {
            while (i < j && s[j] >= x) // 从右向左找第一个小于x的数
                j--;
            if (i < j)
                s[i++] = s[j];
            while (i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                i++;
            if (i < j)
                s[j--] = s[i];
        }
        s[i] = x;
        QS(s, l, i - 1); // 递归调用
        QS(s, i + 1, r);
    }
}