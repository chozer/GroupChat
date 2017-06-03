package chat.cilent.feather;

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
	
	//ConNet con;
	//Socket cilent;
	LogDevice log;

	
	static String s = "";
	String name ;
	DataOutputStream out ;

	public frame(String name,Socket cilent) throws Exception{
		this.name = name;
		//con =new  ConNet();
		//cilent = con.Connection();
		log = new LogDevice(cilent);
		
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
		add(list,BorderLayout.EAST);
		add(rece,BorderLayout.NORTH);
		add(text,BorderLayout.CENTER);
		add(send,BorderLayout.SOUTH);
		setVisible(true);
		out = new DataOutputStream(cilent.getOutputStream());
		new Thread(new Receive(cilent)).start();
		//new Thread(new MetaList(list)).start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

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
/*
//维护列表
class MetaList extends Thread{
	List list;
	MetanList mentan = new MetanList();
	public MetaList(List list) throws SQLException{
		this.list = list;
		new Thread(new MetanList()).start();
	}
	public void add(){
		String[] li = list.getSelectedItems();
		String[] alive = mentan.newUser(li);
		for(int i = 0;i < alive.length;i++){
			list.add(alive[i]);
			System.out.println(alive[i]);
		}
			
	}
	public void rem(){
		String[] li = list.getSelectedItems();
		String[] died = mentan.die(li);
		for(int i = 0;i < died.length;i++)
			list.remove(died[i]);
	}
	public void run(){
		while(true){
			add();
			rem();
		}
			
	}
}*/
	
//默认关闭
class WinCilent extends WindowAdapter{
	frame me;
	Socket ce;
	WinCilent(frame m,Socket cilent){
		me = m;
		ce = cilent;
	}
	
	public void windowClosing(WindowEvent e){
		
			try {
				log.logout();
				ce.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		me.dispose();
		System.exit(0);
	}
}
}



