package test.thread.chat;

import java.io.*;
import java.net.*;
import java.util.*;
public class server {
	private List <chat> list = new ArrayList<chat>();
		public static void main(String[] args){
			try {
				new  server().start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	private void start() throws IOException{
		ServerSocket server = new ServerSocket(8888);
			while(true){
				Socket cilent = server.accept();
				System.out.println(cilent.getInetAddress()+"linking");
				chat chat = new chat(cilent);
				list.add(chat);
				new Thread(chat).start();
			}
	}
		
    
	private class chat implements Runnable{
	//�������������
	private DataInputStream in ;
	private DataOutputStream out;
	private String name;
	private boolean isRunning = true;
	chat(Socket cilent) {
		try {
			//��ȡ���������
			in = new DataInputStream(cilent.getInputStream());
			out = new DataOutputStream(cilent.getOutputStream());
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
			mes = in.readUTF();
			
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
	//run
	public void run(){
		while(isRunning){
			//send(recieve());
			sendOther();
		}
	}
}
}




