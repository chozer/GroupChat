package test.thread.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class frame extends Frame implements ActionListener{
	static TextArea text;
	static TextArea rece;
	Button send;
	Button sign;
	TextField tname;
	Panel sname;
	static String s = "";
	static String name = "x";
	DataOutputStream out ;
	/*public void sframe(){
		
		setTitle("Login");
		setLayout(new GridLayout(1,3));
		Label name = new Label("name");
		sign = new Button("sign");
		tname = new TextField();
		this.addWindowListener(new WinCilent(this));
		sign.addActionListener(this);
		add(name);
		add(tname);
		add(sign);
		setVisible(true);
	}*/
	public frame(Socket cilent) throws IOException{
		setTitle("Cilent");
		setLayout(new BorderLayout());
		sname = new Panel();
		sname.setLayout(new GridLayout(2,1));
		send = new Button("send");
		sign = new Button("change");
		this.addWindowListener(new WinCilent(this));
		send.addActionListener(this);
		sign.addActionListener(this);
		text = new TextArea(40,60);
		rece = new TextArea(10,50);
		tname = new TextField();
		send.setSize(WIDTH, 30);
		sname.add(tname,BorderLayout.NORTH);
		sname.add(sign, BorderLayout.SOUTH);
		add(sname,BorderLayout.WEST);
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
		if(e.getSource().equals(sign)){
			name = tname.getText();
			System.out.println(name);
		}
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
	WinCilent(frame m){
		me = m;
	}
	public void windowClosing(WindowEvent e){
		me.disable();
		System.exit(0);
	}
}