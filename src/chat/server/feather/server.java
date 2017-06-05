package chat.server.feather;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
public class server {
	
	private List <chat> list = new ArrayList<chat>();
	private List <String> namelist = new ArrayList<String>();
		public static void main(String[] args){
			try {
				new  server().start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	private void start() throws Exception{
		ConBase con;
		ServerSocket server = new ServerSocket(8888);
			while(true){
				con = new ConBase();
				Socket cilent = server.accept();
				System.out.println(cilent.getInetAddress()+"linking");
				chat chat = new chat(cilent);
				System.out.println(cilent.getInetAddress().getHostName());
				list.add(chat);
				namelist.add(chat.name);
				new Thread(chat).start();
				new Thread(new MetanList(cilent)).start();
			}
	}
		
    
	private class chat implements Runnable{
	//创建输入输出流
	/*
	String na; 
	private byte[] re;
	private DatagramSocket udp;
	private DatagramPacket packet;
	*/
	LandDetec land;
	Socket cilent;
	MetanList metalist;
	
	private DataInputStream in ;
	private DataOutputStream out;
	private String name;
	String[] userlist;
	
	private boolean Running = true;
	chat(Socket cilent) {
		try {
			this.cilent = cilent;
			//获取输入输出流
			in = new DataInputStream(cilent.getInputStream());
			out = new DataOutputStream(cilent.getOutputStream());
			land = new LandDetec(cilent);
			land.select();
			this.name = land.uname;
			/*
			//建立udp套接字，用于接收用户名
			udp = new DatagramSocket(5678);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Running = false;
			closeutil.closeAll(in,out);
			list.removeAll(list);
		}
	}
	
	//接受数据
	private String recieve(){
		String mes = "";
		try {
			//信息接受
			mes = in.readUTF();
			/*
			//发送目标接收
			re = new byte[64];
			packet = new DatagramPacket(re,re.length);
			udp.receive(packet);
			na = packet.getData().toString();*/
			if(mes == null || mes.equals(""))
				return "";
			
				
			//System.out.println("receive news"+mes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Running = false;
			closeutil.closeAll(in,out);
			list.removeAll(list);
		}
		return mes;
	}
	
	//发送数据
	private void send(String mes){
		
		if(mes == null || mes.equals("")){
			return ;
		}
		try {
			out.writeUTF(mes);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Running = false;
			closeutil.closeAll(in,out);
			list.removeAll(list);
		}
	}
	//发送给其他客户端
	private void sendOther(String mes) throws IOException{
		//下线检测
		if(mes.equals("logout*$#@%@#$@#$%!@#^^&*$%#")){
			try {
				land.logout();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cilent.close();
		}else if(mes.startsWith("to-")){
			String[] me = null;
			me = mes.split("-|:");
			for(String user:namelist){
				if(user.equals(me[1])){
					System.out.println(me[3]+"-"+me[2]);
					list.get(namelist.indexOf(user)).send("chat*"+me[3]+"*"+me[2]);
				}
			}
		}else{
			for(chat temp:list){
				if(temp == this)
					continue;
				else
					temp.send(mes);
			}			
		}

	}
	/*
	//私聊
	private void sendWho(){
		String mes = this.recieve();
		for(String temp:namelist){
			if(temp == na)
				
		}
	}*/
	//run
	public void run(){
		
		while(Running){
			try {
				sendOther(this.recieve());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
}