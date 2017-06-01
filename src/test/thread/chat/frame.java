package test.thread.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class frame extends Frame implements ActionListener{
	static TextArea text;
	static TextArea rece;
	Button send;
	Button sign;
	TextField tname;
	Panel sname;
	List list;
	ConNet con;
	ConBase conbase;
	Socket cilent;
	static String s = "";
	static String name ;
	DataOutputStream out ;

	public frame(String name) throws IOException, SQLException{
		this.name = name;
		con =new  ConNet();
		conbase = new ConBase();
		cilent = con.Connection();
		//在数据库中插入mac地址
		conbase.insetIp(name, cilent.getInetAddress().getHostName());
		setTitle("Cilent");
		setLayout(new BorderLayout());
		sname = new Panel();
		sname.setLayout(new GridLayout(2,1));
		send = new Button("send");
		sign = new Button("change");
		this.addWindowListener(new WinCilent(this,cilent));
		send.addActionListener(this);
		sign.addActionListener(this);
		list = new List(10);
		text = new TextArea(40,60);
		rece = new TextArea(10,50);
		tname = new TextField();
		send.setSize(WIDTH, 30);
		/*sname.add(tname,BorderLayout.NORTH);
		sname.add(sign, BorderLayout.SOUTH);
		add(sname,BorderLayout.WEST);*/
		//add(list,BorderLayout.EAST);
		add(rece,BorderLayout.NORTH);
		add(text,BorderLayout.CENTER);
		add(send,BorderLayout.SOUTH);
		setVisible(true);
		out = new DataOutputStream(cilent.getOutputStream());
		new Thread(new Receive(cilent)).start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*if(e.getSource().equals(sign)){
			name = tname.getText();
		}*/
		if(e.getSource().equals(send)){
			
			s = text.getText();
			rece.append(name+"-"+s+"\n");
		try {
			out.writeUTF(name+"-"+s);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		
	}
	public  static String get(){
		return s;
	}
	
	public static void put(String msg){
		rece.append(msg+"\n");
	}	
}



class WinCilent extends WindowAdapter{
	frame me;
	Socket ce;
	WinCilent(frame m,Socket cilent){
		me = m;
		ce = cilent;
	}
	
	public void windowClosing(WindowEvent e){
		try {
			ce.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		me.dispose();
		System.exit(0);
	}
}