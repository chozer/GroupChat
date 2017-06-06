package chat.cilent.feather;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;


public class frame extends Frame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static TextArea text;
	static TextArea rece;
	Button send;
	Button sign;
	TextField tname;
	Panel sname;
	static List list;
	
	//ConNet con;
	//Socket cilent;
	LogDevice log;

	
	static String s = "";
	String name ;
	DataOutputStream out ;

	public frame(String name,Socket cilent) throws Exception{
		this.name = name;
		log = new LogDevice(cilent);
		Font f = new Font("TimesRoman",Font.PLAIN,15);
		
		setTitle(name);
		setSize(500,600);
		setLayout(new BorderLayout());
		sname = new Panel();
		sname.setLayout(new GridLayout(2,1));
		send = new Button("send");
		sign = new Button("change");
		list = new List(10);
		text = new TextArea(40,60);
		rece = new TextArea(10,50);
		tname = new TextField();
		send.setSize(WIDTH, 30);
		send.addActionListener(this);
		sign.addActionListener(this);
		list.addActionListener(this);
		this.addWindowListener(new WinCilent(this,cilent));
		text.setFont(f);
		rece.setFont(f);
		add(list,BorderLayout.EAST);
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
		if(e.getSource().equals(list)){
			text.setForeground(Color.PINK);
			text.append("To-"+e.getActionCommand()+":");
		}
		if(e.getSource().equals(send)){
			
			s = text.getText();
			text.setText("");
			if(s.startsWith("To-")){
				text.setForeground(Color.black);
				rece.append(s+"\n");
				try {
					out.writeUTF(s+"-"+name);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else{
				text.setText("");
				rece.append(name+"--"+s+"\n");
				try {
					out.writeUTF(name+"--"+s);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	public  static String get(){
		return s;
	}
	
	public static void put(String msg){
		rece.append(msg+"\n");
	}
	public static void putList(String user){
		//for(String user:li)
			list.add(user);	
	}
	public static void rmList(String user){
		list.remove(user);
	}


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



