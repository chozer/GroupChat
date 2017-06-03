package chat.server.feather;
import java.sql.*;
public class ConBase {
    public Connection con = null;
    Statement inset;
    Statement seach;
    ResultSet rs;
    public ConBase(){
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/feather?characterEncoding=utf8&useSSL=true";
			String user = "root";
			String pass ="qaz1521756898";
			con = DriverManager.getConnection(url,user,pass);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //注册插入新用户
    public void insert(String name,String pass) throws SQLException{
    	inset = con.createStatement();
    	inset.executeUpdate("INSERT INTO user (name , pass)values ('"+name+"','"+pass+"')" );
    	}
    //登陆查询
    public boolean search(String uname,String upass) throws SQLException{
    	boolean flag = false;
    	seach = con.createStatement();
    	rs = seach.executeQuery("SELECT name,pass FROM user");
    	while(rs.next()){
    		if(rs.getString(1).equals(uname)&&rs.getString(2).equals(upass))
    			flag = true;
    	}
    	return flag;
    }
    //注册查询
    public boolean search(String uname) throws SQLException{
    	boolean flag = false;
    	Statement seach_sign_up;
    	ResultSet rss;
    	seach_sign_up = con.createStatement();
    	rss = seach_sign_up.executeQuery("SELECT name,pass FROM user");
    	while(rss.next()){
    		if(rss.getString(1).equals(uname))
    			flag = true;
    	}
    	return flag;
    }
    //在线状态查询
    public boolean seaLog(String name) throws SQLException{
    	Statement sealog = con.createStatement();
    	ResultSet rslog;
    	rslog = sealog.executeQuery("SELECT login FROM user WHERE name = '"+name+"'");
    	rslog.next();
    	return rslog.getBoolean("login");
    }
    //插入mac地址ַ
    public void insetIp(String name,String mac) throws SQLException{
    	Statement inst = con.createStatement();
    	inst = con.createStatement();
    	inst.executeUpdate("UPDATE user SET mac = '"+mac+"' WHERE name='"+name+"'");
    }
    //查询地址ַ
    public String seachName(String mac) throws SQLException{
    	Statement sea = con.createStatement();
    	ResultSet rr  ;
    	rr = sea.executeQuery("SELECT name FROM user WHERE mac = '"+mac+"'");
    	rr.next();
    	return rr.getString("name");
    }
    //上线
    public void login(String name) throws SQLException{
    	Statement login = con.createStatement();
    	login.executeUpdate("UPDATE user SET login = 'true' WHERE name = '"+name+"'");
    }
    //下线
    public void logout(String name) throws SQLException{
    	Statement logout = con.createStatement();
    	logout.executeUpdate("UPDATE user SET login = 'false' WHERE name = '"+name+"'");
    }
    //查询在线用户
    public String[] seaUserIn() throws SQLException{
    	String[] userin = new String[20];
    	int i = 0;
    	Statement seaUserin =con.createStatement();
    	ResultSet userIn;
    	userIn = seaUserin.executeQuery("SELECT name FROM user WHERE login = true ");
    	while(userIn.next()){
    		userin[i] = userIn.getString(1);
    		i++;
    	}
    	return userin;
    }
   /* public Connection getConnection(){
    	
    	return con;
    	
    }*/
}
