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
			new frame();
			new Thread(new send(cilent)).start();
			new Thread(new receive(cilent)).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class send implements Runnable{
	private BufferedReader console ;
	private DataOutputStream out ;
	String s ;
	String m ;
	public send(){
		console  = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public send(Socket cilent){
		this();
		try {
			out = new DataOutputStream(cilent.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeutil.closeAll(out);
		}
	}
	
	
	//获取控制台输入
	public String getConsole() throws IOException{
		return console.readLine();
	}
	
	//发送数据
	public void send1(){
		//s = "";
		try {
			//System.out.println("your name:");
			//String name = getConsole();
			//System.out.println("news:");
			/*m = frame.get();
			System.out.println(m.equals(s));
			if( m.equals(s) )
				return ; 
			
			s = m;
			String msg = getConsole();*/
			//String msg = frame.get();
			
			String msg = frame.s;
			System.out.println(msg);
			if(msg != null && !msg.equals("")){
				//out.writeUTF(name);
				//System.out.println("猴赛雷");
				out.writeUTF(msg);
				out.flush();
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeutil.closeAll(out);
		}
		
	}
	//run
	public void run(){
		while(true)
			send1();
		
			
	}
}
class receive implements Runnable{
	private DataInputStream in ;
	//创建输入流
	public receive(Socket cilent){
		try {
			in = new DataInputStream(cilent.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeutil.closeAll(in);
		}
	}
	//接收数据
	public void receive1() {
		String mes = "";
		try {
			mes = in.readUTF();
			if(!mes.equals(""))
				frame.put(mes);
			//System.out.println(in.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeutil.closeAll(in);
		}
	}
	//run
	public void run(){
		while(true)
		receive1();
			
	}
}