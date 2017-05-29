package test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class cilent {
	public static void main(String[] args){
		String msg = "name";
		try {
			DatagramSocket cilent = new DatagramSocket(6666);
			byte[]  b = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(b,b.length,new InetSocketAddress("localhost",8888));
			cilent.send(packet);
			cilent.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
