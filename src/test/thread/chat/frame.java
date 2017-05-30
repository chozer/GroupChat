package test.thread.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class frame extends Frame implements ActionListener{
	static TextArea text;
	static TextArea rece;
	//PrintWriter out;
	Button send = new Button("send");
	static String s = "";
	//Send sen;
	DataOutputStream out ;
	public frame(Socket cilent) throws IOException{
		setTitle("cilent");
		setLayout(new BorderLayout());
		this.addWindowListener(new WinCilent(this));
		send.addActionListener(this);
		text = new TextArea(40,60);
		rece = new TextArea(10,50);
		send.setSize(WIDTH, 30);
		add(send,BorderLayout.SOUTH);
		add(text,BorderLayout.CENTER);
		add(rece,BorderLayout.NORTH);
		setVisible(true);
		//new Thread(new Send(cilent)).start();
		//sen = new Send();
		out = new DataOutputStream(cilent.getOutputStream());
		new Thread(new Receive(cilent)).start();
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
		//sen.send1();
		try {
			out.writeUTF(s);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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