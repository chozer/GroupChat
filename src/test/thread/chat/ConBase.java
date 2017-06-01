package test.thread.chat;
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
    //注册 插入新用户
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
    //插入mac地址
    public void insetIp(String name,String mac) throws SQLException{
    	Statement inst = con.createStatement();
    	inst = con.createStatement();
    	inst.executeUpdate("UPDATE user SET mac = '"+mac+"' WHERE name='"+name+"'");
    }
    //查询地址
    public String seachName(String mac) throws SQLException{
    	Statement sea = con.createStatement();
    	ResultSet rr  ;
    	rr = sea.executeQuery("SELECT name FROM user WHERE mac = '"+mac+"'");
    	rr.next();
    	//String name = rr.getString(1);
    	//System.out.println(name);
    	return rr.getString("name");
    }
   /* public Connection getConnection(){
    	
    	return con;
    	
    }*/
}
