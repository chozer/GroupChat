package test.thread.chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ConNet {
	Socket cilent;
	/*public ConNet()throws IOException{
		InetAddress addr = InetAddress.getByName("LAPTOP-R10M96M9");
		System.out.println(addr.getHostName());
		Socket cilent = new Socket(addr,8888);
	}*/
	//客户端套接字创建
	public Socket Connection()throws IOException {
		InetAddress addr = InetAddress.getByName("LAPTOP-R10M96M9");
		System.out.println(addr.getHostName());
		Socket cilent = new Socket(addr,8888);
		return cilent;
	}
}
