package test.thread.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class cilent {
	public static void main(String[] args) {
		try {
			InetAddress addr = InetAddress.getByName("LAPTOP-R10M96M9");
			System.out.println(addr.getHostName());
			Socket cilent = new Socket(addr,8888);
			new frame(cilent);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


