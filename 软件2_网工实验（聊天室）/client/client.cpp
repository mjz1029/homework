#include <stdio.h>
#pragma warning(disable:4996)
//#include <windows.h>
#include <graphics.h> //easyX
#pragma comment(lib, "ws2_32.lib")

SOCKET clientSocket;
HWND hWnd;
int count = 0;

void jieshou() {
	char recvBuff[1024];
	int r;
	while (1) {
		r = recv(clientSocket, recvBuff, 1023, NULL);
		if (r > 0) {
			recvBuff[r] = 0;
			outtextxy(0, count * 20, recvBuff);
			count++;
		}
	}
}

int main() {
	//初始化界面
	hWnd = initgraph(300, 400, SHOWCONSOLE);

	//1 请求协议版本
	WSADATA wsaData;
	WSAStartup(MAKEWORD(2, 2), &wsaData);
	if (LOBYTE(wsaData.wVersion) != 2 ||
		HIBYTE(wsaData.wVersion) != 2) {
		printf("请求协议版本失败!\n");
		return -1;
	}
	printf("请求协议成功!\n");
	//2 创建socket
	clientSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (SOCKET_ERROR == clientSocket) {
		printf("创建socket失败!\n");
		WSACleanup();
		return -2;
	}
	printf("创建socket成功!\n");

	//3 获取服务器协议地址族
	SOCKADDR_IN addr = { 0 };
	addr.sin_family = AF_INET;//协议版本
	addr.sin_addr.S_un.S_addr = inet_addr("10.2.17.225");//用自己的ip
	addr.sin_port = htons(10086);//0 - 65535     10000左右
	//os内核 和其他程序  会占用掉一些端口   80  23  

	//4 连接服务器
	int r = connect(clientSocket, (sockaddr*)&addr, sizeof addr);
	if (r == -1) {
		printf("连接服务器失败!\n");
		return -1;
	}
	printf("连接服务器成功!\n");

	//5 通信
	char buff[1024] = { 0 };
	CreateThread(NULL, NULL,
		(LPTHREAD_START_ROUTINE)jieshou,
		NULL, NULL, NULL);
	while (1) {
		memset(buff, 0, 1024);
		printf("你想说啥:");
		scanf("%s", buff);
		send(clientSocket, buff, strlen(buff), NULL);
	}






	return 0;
}
