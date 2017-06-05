package chat.server.feather;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;

public class MetanList extends Thread{
	ConBase con ;
	String[] usernow;
	String[] userold;
	Socket cilent;
	DataOutputStream out;
	
	public MetanList(Socket cilent) throws Exception{
		this.cilent = cilent;
		con = new ConBase();
		out = new DataOutputStream(cilent.getOutputStream());
		usernow = new String[30];
		userold = new String[30];
		userold = con.seaUserIn();
		out.writeUTF("list@$#");
		for(String user:userold){
			if(user!=null)
			out.writeUTF(user);
		}
			
		out.writeUTF("@$#list");
	}
	public void Metan() throws SQLException{
		usernow = con.seaUserIn();
	}
	//查询用户变化
	public boolean chsure(String[] list){
		boolean li = list.equals(usernow);
		return li;
	}
	
	
	//确定婴儿
	public void baby(String[] list) throws IOException{
		String[] alive = new String[20];
		int i = 0;
		for(String user:usernow){
			if(user!=null)
				if(seaAlive(user,list)){
					alive[i] = user;
					i++;
				}
		}
		//发送婴儿目录
		if(alive.length > 0){
			out.writeUTF("list@$#");
			for(String user:alive){
				if(user!=null)
				out.writeUTF(user);
			}
			out.writeUTF("@$#list");
		}
		
	}
	//确定老人
	public void older(String[] list) throws IOException{
		String[] died = new String[20];
		int i = 0;
		for(String user:list){
			if(user!=null)
				if(seaDied(user)){
					died[i] = user;
					i++;
				}
		}
		//发送老人目录
		if(died.length > 0){
			out.writeUTF("@list$#");
			for(String user:died){
				if(user!=null)
					out.writeUTF(user);
			}
			out.writeUTF("$#list@");
		}
	}
	//搜索died
	public boolean seaDied(String user){
		boolean i = true;
		for(String di:usernow){
			if(di!=null)
				if(user.equals(di))
					i = false;
			}
		return i;
	}
	//搜索alive
	public boolean seaAlive(String user,String[] list){
		boolean i = true;
		for(String alive:list){
			if(alive!=null)
				if(user.equals(alive))
					i = false;
			}
		return i;
	}
	
	
		
	public void run(){
		try {
			while(true){
				Metan();
				if(!chsure(userold)){
					baby(userold);
					older(userold);
					userold = usernow.clone();
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			closeutil.closeAll(out,cilent);
			e.printStackTrace();

		}
	}
	
}
