// 双色分色
// 白色在下，黑色在上，黑色移动至C，白色移动至B
#include <iostream>
using namespace std;

void move(char a, char b)
{
    cout << "move " << a << " to " << b << endl;
}

void hanoi2(int n, char a, char b, char c)
{
    if (n == 2)
    {
        move(a, b);
        move(a, c);
        move(b, c);
    }
    else
    {
        hanoi2(n - 2, a, b, c);
        move(a, b);
        hanoi2(n - 2, c, a, b);
        move(a, c);
        hanoi2(n - 2, b, c, a);
        move(b, c);
        hanoi2(n - 2, a, b, c);
    }
}

void hanoi3(int n, char a, char b, char c)
{
    if (n == 2)
    {
        move(a, c);
        move(a, b);
    }
    else
    {
        hanoi2(n - 2, a, c, b);
        move(a, c);
        hanoi2(n - 2, b, a, c);
        move(a, b);
        hanoi2(n - 2, c, b, a);
        hanoi3(n - 2, a, b, c);
    }
}

int main()
{
    int n;
    cin >> n;
    hanoi3(n, 'A', 'B', 'C');
}