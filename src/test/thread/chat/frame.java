package test.thread.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class frame extends Frame implements ActionListener{
	static TextArea text;
	static TextArea rece;
	PrintWriter out;
	Button send = new Button("send");
	static String s = "";
	public frame(Socket cilent){
		setTitle("cilent");
		setLayout(new BorderLayout());
		this.addWindowListener(new WinCilent(this));
		send.addActionListener(this);
		text = new TextArea(20,50);
		rece = new TextArea(20,50);
		add("South",send);
		add("Center",text);
		//add("Center",rece);
		setVisible(true);
	}
	/*public void mousePressde(MouseEvent e){
		
	}
	public void mouseReleased(MouseEvent e){
		text.setText("");
	}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		s = text.getText();
		text.append("get");
	}
	public static String get(){
		return s;
	}
	
	public static  void put(String msg){
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