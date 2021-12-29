//三色整体
//白色在下，灰色在中，黑色在上
#include <iostream>
using namespace std;

void move(char a, char b)
{
    cout << "move " << a << " to " << b << endl;
}

void hanoi(int n, char a, char b, char c)
{
    if (n == 3)
    {
        move(a, b);
        move(a, b);
        move(a, c);
        move(b, c);
        move(b, c);
    }
    else
    {
        hanoi(n - 3, a, b, c);
        move(a, b);
        move(a, b);
        hanoi(n - 3, c, a, b);
        move(a, c);
        hanoi(n - 3, b, c, a);
        move(b, c);
        move(b, c);
        hanoi(n - 3, a, b, c);
    }
}

int main()
{
    int n;
    cin >> n;
    hanoi(n, 'A', 'B', 'C');
}