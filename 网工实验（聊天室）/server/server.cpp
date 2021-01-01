#include <stdio.h>

#include <windows.h>
#pragma comment(lib, "ws2_32.lib")

SOCKADDR_IN cAddr = { 0 };
int len = sizeof cAddr;
SOCKET clientSocket[1024];
int count = 0;


void tongxin(int idx) {
	char buff[1024];
	int r;
	while (1) {
		r = recv(clientSocket[idx], buff, 1023, NULL);
		if (r > 0) {
			buff[r] = 0;
			printf("%d:%s\n", idx, buff);
			//广播数据
			for (int i = 0; i < count; i++) {
				send(clientSocket[i], buff, strlen(buff), NULL);
			}
		}
	}
}

int main() {
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
	SOCKET serverSocket = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (SOCKET_ERROR == serverSocket) {
		printf("创建socket失败!\n");
		WSACleanup();
		return -2;
	}
	printf("创建socket成功!\n");

	//3 创建协议地址族
	SOCKADDR_IN addr = { 0 };
	addr.sin_family = AF_INET;//协议版本
	addr.sin_addr.S_un.S_addr = inet_addr("10.2.17.225");//用自己的ip
	addr.sin_port = htons(10086);//0 - 65535     10000左右
	//os内核 和其他程序  会占用掉一些端口 

	//4 绑定
	int r = bind(serverSocket, (sockaddr*)&addr, sizeof addr);
	if (-1 == r) {
		printf("bind失败!\n");
		closesocket(serverSocket);
		WSACleanup();
		return -2;
	}
	printf("bind成功!\n");

	//5 监听
	r = listen(serverSocket, 10);
	if (-1 == r) {
		printf("listen失败!\n");
		closesocket(serverSocket);
		WSACleanup();
		return -2;
	}
	printf("listen成功!\n");


	//6 等待客户端连接    阻塞    尾生抱柱
	//客户端协议地址族
	while (1) {
		clientSocket[count] = accept(serverSocket, (sockaddr*)&cAddr, &len);
		if (SOCKET_ERROR == clientSocket[count]) {
			printf("服务器宕机了!\n");
			//8 关闭socket
			closesocket(serverSocket);
			//9 清除协议信息
			WSACleanup();
			return -2;
		}
		printf("有客户端连接到服务器了：%s!\n", inet_ntoa(cAddr.sin_addr));

		CreateThread(NULL, NULL, (LPTHREAD_START_ROUTINE)tongxin,
			(char*)count, NULL, NULL);

		count++;
	}
	return 0;
}
