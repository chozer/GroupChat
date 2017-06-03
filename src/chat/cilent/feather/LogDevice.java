package chat.cilent.feather;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LogDevice {
	Socket cilent;
	DataOutputStream out;
	DataInputStream in;
	public LogDevice(Socket cilent) throws IOException{
		this.cilent = cilent;
		in = new DataInputStream(cilent.getInputStream());
		out = new DataOutputStream(cilent.getOutputStream());
	}
//登陆
	//验证用户信息
	public boolean login(String name,String pass) throws IOException{
		out.writeInt(1);
		out.writeUTF(name);
		out.writeUTF(pass);
		return in.readBoolean();
	}
	//在线冲突检测
	public boolean log() throws IOException{
		return in.readBoolean();
	}
//注册
	public boolean register(String name,String pass) throws IOException{
		out.writeInt(2);
		out.writeUTF(name);
		out.writeUTF(pass);
		return in.readBoolean();
	}
//登出
	public void logout() throws IOException{
		out.writeUTF("logout*$#@%@#$@#$%!@#^^&*$%#");
	}
}
