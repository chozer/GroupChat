package chat.cilent.feather;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

class Receive implements Runnable{
	private DataInputStream in ;
	//获取输入流
	public Receive(Socket cilent){
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
			//接收婴儿名单
			if(mes.equals("list@$#")){
				while(!(mes = in.readUTF()).equals("@$#list"))
					frame.putList(mes);	
			//接收老人名单
			}else if(mes.equals("@list$#")){
				while(!(mes = in.readUTF()).equals("$#list@"))
					frame.rmList(mes);	
			}else if(mes.startsWith("chat~")){
				String[] me = null;
				me = mes.split("~|-");
				
				frame.put("Chat~"+me[1]+"-tell:"+me[2]);
			}else if(!mes.equals(""))
				frame.put(mes);
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
