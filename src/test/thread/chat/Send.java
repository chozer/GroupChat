package test.thread.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Send /*implements Runnable*/{
	private DataOutputStream out ;
	String s ;
	String m ;
	public Send(){
		
	}
	
	public Send(Socket cilent){
		//this();
		try {
			out = new DataOutputStream(cilent.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeutil.closeAll(out);
		}
	}
	

	
	//·¢ËÍÊý¾Ý
	public void send1(){
		//s = "";
		try {
			String msg = frame.get();
			System.out.println(msg);
			if(msg != null && !msg.equals("")){
				//out.writeUTF(name);
				out.writeUTF(msg);
				out.flush();
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			closeutil.closeAll(out);
		}
		
	}
	//run
	/*public void run(){
		while(true)
			send1();
		
			
	}*/
}
