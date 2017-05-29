package test.udp.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class receive implements Runnable{
	private DatagramSocket cilent;
	private DatagramPacket packet;
	private byte[] data;
	public receive(DatagramSocket cilent) throws SocketException{
		//创建客户端
	}
	//接受数据
	public void receive1() throws IOException{
		data = new byte[1024];
		packet = new DatagramPacket(data,data.length);
		cilent.receive(packet);
		data = packet.getData();
		System.out.println(new String(data));
	}
	public void run(){
		try {
			while(true)
			receive1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			closeutil.closeAll(cilent);
		}
	}
}
