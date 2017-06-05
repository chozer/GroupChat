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
	boolean flag;
	public LandDetec(Socket cilent) throws IOException{
		flag = false;
		this.cilent = cilent;
		in = new DataInputStream(cilent.getInputStream());
		out = new DataOutputStream(cilent.getOutputStream());
		con = new ConBase();
	}
	//功能选择器
	public void select() throws IOException, SQLException{
		while(!flag){
			String f = in.readUTF();
			if(f.startsWith("1,"))
				login(f);
			if(f.startsWith("2,"))
				register(f);
		}
		
	}
	//登陆_1
	public void login(String mes) throws IOException, SQLException{
		String name;
		String pass;
		String[] info;
			info = mes.split(",");
			name = info[1];
			pass = info[2];
			if(con.search(name, pass)){
				out.writeBoolean(true);
				if(!con.seaLog(name)){
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
	//注册_2
	public void register(String mes) throws IOException, SQLException{
		String name;
		String pass;
		String[] info;
		info = mes.split(",");
		name = info[1];
		pass = info[2];
		if(!con.search(name)){
			con.insert(name, pass);
			con.login(name);
			//在数据库中插入mac地址
			con.insetIp(name, cilent.getInetAddress().getHostName());
			flag = true;
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
