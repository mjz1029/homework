#include <iostream>
#include <iomanip>
using namespace std;
int main()
{
    //假设A是罪犯
    unsigned int killer = 'A';
    int flags = 0; //A、B、C、D说真话的值为1，说假话为0，四个人所说真假的和
                   //假设A、B、C、D都是罪犯，遍历四种可能
    for (killer = 'A'; killer <= 'D'; killer++)
    {
        flags = (killer == 'B' || killer == 'C' || killer == 'D');
        flags += (killer == 'C');
        flags += (killer == 'A' || killer == 'D');
        flags += (killer == 'C');
        if (flags == 2) //A、B、C、D中有两个人说的是真话
        {
            cout << "killer is : " ;
            cout.put(killer);
        }
    }
    return 0;
}
