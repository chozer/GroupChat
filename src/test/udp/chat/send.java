package test.udp.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class send implements Runnable{
	private BufferedReader console;
	private byte[] data;
	private DatagramSocket cilent;
	String name;
		public send(DatagramSocket cilent) throws SocketException{
			//创建客户端
			console = new BufferedReader(new InputStreamReader(System.in));
		}
		//发送数据
		public void send1() throws IOException{
			cilent.send(packet());
		}
		//获取控制台输入
		public String getConsole() throws IOException{
			String msg ="";
			while(true){
				msg = console.readLine();
				if(msg != null || !msg.equals(""))
				return msg;
				else 
					continue;
			}	
		}
		//打包数据
		public DatagramPacket packet() throws IOException{
			data = getConsole().getBytes();
			InetAddress addr = InetAddress.getByName("localhost");
			return new DatagramPacket(data,data.length,addr,6666);
		}
		
		public void run(){
			try {
				while(true)
				send1();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				closeutil.closeAll(cilent);
			}
		}
}
