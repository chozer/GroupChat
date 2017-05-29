package test.udp.chat;

import java.io.*;
import java.net.*;
import java.util.*;
public class server{
	private DatagramSocket server;
	private DatagramPacket packet;
	public static void main(String[] args){
		while(true){
			try {
				new server().trans();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private server() throws SocketException{
		server = new DatagramSocket(6666);
	}
	//转发数据
	/*private void trans() throws IOException{
		byte[] data = new byte[1024];
		packet = new DatagramPacket(data,data.length);
		server.receive(packet);
		data =packet.getData();
		DatagramPacket out = new DatagramPacket(data,data.length,packet.getAddress(),packet.getPort());
		server.send(out);
		server.close();
	}*/
	private void trans() throws IOException{
		byte[] data = new byte[1024];
		packet = new DatagramPacket(data,data.length);
		server.receive(packet);
		data =packet.getData();
		System.out.print(new String(data));
		DatagramPacket out = new DatagramPacket(data,data.length,new InetSocketAddress("localhost",5678));
		server.send(out);
		server.close();
	}
}





