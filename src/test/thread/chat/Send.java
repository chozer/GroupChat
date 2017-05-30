package test.thread.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Send /*implements Runnable*/{
	private BufferedReader console ;
	private DataOutputStream out ;
	String s ;
	String m ;
	public Send(){
		//console  = new BufferedReader(new InputStreamReader(System.in));
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
			String msg = frame.get();
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
	/*public void run(){
		while(true)
			send1();
		
			
	}*/
}
