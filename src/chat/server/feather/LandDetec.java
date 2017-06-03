package chat.server.feather;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class LandDetec {
	DataInputStream in ;
	DataOutputStream out;
	ConBase con;
	Socket cilent;
	String uname;
	public LandDetec(Socket cilent) throws IOException{
		this.cilent = cilent;
		in = new DataInputStream(cilent.getInputStream());
		out = new DataOutputStream(cilent.getOutputStream());
		con = new ConBase();
	}
	//功能选择器
	public void select() throws IOException, SQLException{
		int f = in.readInt();
		if(f==1)
			login();
		if(f==2)
			register();
	}
	//登陆_1
	public void login() throws IOException, SQLException{
		String name;
		String pass;
		
		boolean flag = false;
		while(!flag){
			name = in.readUTF();
			pass = in.readUTF();
			if(con.search(name, pass)){
				System.out.println(name+"-"+pass);
				out.writeBoolean(true);
				if(!con.seaLog(name)){
					System.out.println(name+","+pass);
					con.login(name);
					this.uname = name;
					//在数据库中插入mac地址
					con.insetIp(name, cilent.getInetAddress().getHostName());
					flag = true;
					out.writeBoolean(false);
				}else
					out.writeBoolean(true);		
			}else
				out.writeBoolean(false);
		}
	}
	//注册_2
	public void register() throws IOException, SQLException{
		String name;
		String pass;
		name = in.readUTF();
		pass = in.readUTF();
		if(!con.seaLog(name)){
			con.insert(name, pass);
			this.uname = name;
			out.writeBoolean(true);
		}else
			out.writeBoolean(false);		
	}
	//登出_3
	public void logout() throws SQLException{
		con.logout(uname);
	}
	
}
