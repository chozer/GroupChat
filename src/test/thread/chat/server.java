package test.thread.chat;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
public class server {
	
	private List <chat> list = new ArrayList<chat>();
	//private List <String> namelist = new ArrayList<String>();
		public static void main(String[] args){
			try {
				new  server().start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	private void start() throws IOException, SQLException{
		ConBase con;
		ServerSocket server = new ServerSocket(8888);
			while(true){
				con = new ConBase();
				Socket cilent = server.accept();
				System.out.println(cilent.getInetAddress()+"linking");
				chat chat = new chat(cilent);
				chat.name = con.seachName(cilent.getInetAddress().getHostName());
				list.add(chat);
				//namelist.add(chat.name);
				new Thread(chat).start();
			}
	}
		
    
	private class chat implements Runnable{
	//�������������
	/*
	String na; 
	private byte[] re;
	private DatagramSocket udp;
	private DatagramPacket packet;
	*/
	private DataInputStream in ;
	private DataOutputStream out;
	private String name;
	private boolean isRunning = true;
	chat(Socket cilent) {
		try {
			//��ȡ���������
			in = new DataInputStream(cilent.getInputStream());
			out = new DataOutputStream(cilent.getOutputStream());
			/*
			//����udp�׽��֣����ڽ����û���
			udp = new DatagramSocket(5678);*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isRunning = false;
			closeutil.closeAll(in,out);
			list.removeAll(list);
		}
	}
	
	//��������
	private String recieve(){
		String mes = "";
		try {
			//name = in.readUTF();
			//��Ϣ����
			mes = in.readUTF();
			/*
			//����Ŀ�����
			re = new byte[64];
			packet = new DatagramPacket(re,re.length);
			udp.receive(packet);
			na = packet.getData().toString();*/
			if(mes == null || mes.equals(""))
				return "";	
			//System.out.println("receive news"+mes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isRunning = false;
			closeutil.closeAll(in,out);
			list.removeAll(list);
		}
		return mes;
	}
	
	//��������
	private void send(String mes){
		
		if(mes == null || mes.equals("")){
			return ;
		}
		try {
			out.writeUTF(mes);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isRunning = false;
			closeutil.closeAll(in,out);
			list.removeAll(list);
		}
	}
	//���͸������ͻ���
	private void sendOther(){
		String mes = this.recieve();
		for(chat temp:list){
			if(temp == this)
				continue;
			else
				temp.send(mes);
		}
	}
	/*
	//˽��
	private void sendWho(){
		String mes = this.recieve();
		for(String temp:namelist){
			if(temp == na)
				
		}
	}*/
	//run
	public void run(){
		while(isRunning){
			//send(recieve());
			sendOther();
		}
	}
}
}




