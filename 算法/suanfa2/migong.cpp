//求迷宫最短路径
/*
1 1 1 1 1 1 1 1
1 0 3 3 0 0 0 1
1 0 0 3 0 3 0 1
1 3 0 0 0 3 0 1
1 0 0 3 3 0 0 1
1 0 3 3 3 0 3 1
1 0 3 0 0 0 0 1
1 1 1 1 1 1 1 1
*/
//将原迷宫转换为1和2的数据
/* （输入样例）
6 6
1 2 2 1 1 1
1 1 2 1 2 1
2 1 1 1 2 1
1 1 2 2 1 1
1 2 2 2 1 2
1 2 1 1 1 1
1 1 6 6
*/
//毛济洲20182344050
#include <iostream>
using namespace std;
int m, n, p, q, t = 99999999; //终点坐标
int a[100][100];              //1表示空地，2表示障碍物
int v[100][100];              //0表示未访问
int dx[4]={0,1,0,-1};
int dy[4]={1,0,-1,0};
void dfs(int x, int y, int step)

{
    if (x == p && y == q)
    {
        if (step < t)
        {
            t = step;
        }
        return;

    } 
    //顺时针试探
    for(int k=0;k<=3;k++){
        int tx,ty;
        tx=x+dx[k];
        ty=y+dy[k];
        if(a[tx][ty]==1 && v[tx][ty]==0){
            v[tx][ty]=1;
            dfs(tx,ty,step+1);
            v[tx][ty]=0;
        }
    }
    /*
    //右
    if (a[x][y + 1] == 1 && v[x][y + 1] == 0)
    {
        v[x][y + 1] = 1;
        dfs(x, y + 1, step + 1);
        v[x][y + 1] = 0;
    }
    //下
    if (a[x + 1][y] == 1 && v[x + 1][y] == 0)
    {
        v[x + 1][y] = 1;
        dfs(x + 1, y, step + 1);
        v[x + 1][y] = 0;
    }
    //左
    if (a[x][y - 1] == 1 && v[x][y - 1] == 0)
    {
        v[x][y - 1] = 1;
        dfs(x, y - 1, step + 1);
        v[x][y - 1] = 0;
    }
    //上
    if (a[x - 1][y] == 1 && v[x - 1][y] == 0)
    {
        v[x - 1][y] = 1;
        dfs(x - 1, y, step + 1);
        v[x - 1][y] = 0;
    }
    return;
    */
}

int main()
{
    int startx, starty;
    scanf("%d%d", &m, &n);
    for (int i = 1; i <= m; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            scanf("%d", &a[i][j]); //1表示空地，2表示障碍物
        }
    }
    scanf("%d%d%d%d", &startx, &starty, &p, &q);
    v[startx][starty] = 1;
    dfs(startx, starty, 0);
    printf("一共要走%d步", t);

    return 0;
}
