#include <iostream>
using namespace std;

int ans[100][2];
int total = 0;

bool vis[100][100];
//row colum
int horseRoad[5][2] = {{0, 0}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};

void print(int k)
{
    total++;
    cout << total << ": " << endl;
    cout << "(" << 0 << "," << 0 << ")"
         << " ";
    for (int i = 1; i <= k; i++)
    {
        cout << "(" << ans[i][0] << "," << ans[i][1] << ")"
             << " ";
    }
    cout << endl;
}

void search(int r, int c, int k)
{
    if (vis[4][8] == 1)
        print(k - 1);
    else
        for (int i = 1; i <= 4; i++)
        {
            int r1 = r + horseRoad[i][0];
            int c1 = c + horseRoad[i][1];
            if (r1 >= 0 && r1 <= 4 && c1 >= 0 && c1 <= 8)
            {
                ans[k][0] = r1;
                ans[k][1] = c1;
                vis[r1][c1] = 1;
                search(r1, c1, k + 1);
                vis[r1][c1] = 0;
            }
        }
}

int main()
{
    search(0, 0, 1);
    cout << total << endl;
    return 0;
}