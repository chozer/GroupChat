package test;

import java.io.IOException;
import java.net.*;

public class server {
	public static void main(String[] args){
		byte[] b = new byte[1024];
		try {
			DatagramSocket server = new DatagramSocket(8888);
			DatagramPacket packet = new DatagramPacket(b,b.length);
			server.receive(packet);
			b = packet.getData();
			System.out.println(new String(b));
			server.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
