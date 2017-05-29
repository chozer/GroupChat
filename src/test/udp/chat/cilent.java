package test.udp.chat;

import java.io.*;
import java.net.DatagramSocket;
public class cilent {
	public static void main(String[] args) throws IOException{
		DatagramSocket cilent = new DatagramSocket(5678);
		
			new Thread(new send(cilent)).start();
			new Thread(new receive(cilent)).start();
		
	}
}
