package test.thread.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

class Receive implements Runnable{
	private DataInputStream in ;
	//����������
	public Receive(Socket cilent){
		try {
			in = new DataInputStream(cilent.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeutil.closeAll(in);
		}
	}
	//��������
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
