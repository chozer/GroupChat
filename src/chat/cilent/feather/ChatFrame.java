package chat.cilent.feather;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ChatFrame extends Frame implements ActionListener{
	TextArea in;
	TextArea tout;
	Button send;
	Socket cilent;
	DataOutputStream out;
	String name;
	
	public ChatFrame(Socket cilent,String name) throws IOException{
		this.cilent = cilent;
		this.name = name;
		out = new DataOutputStream(cilent.getOutputStream());
		setSize(300,300);
		setLayout(new GridLayout(3,1));
		in = new TextArea();
		tout = new TextArea();
		send = new Button("send");
		send.addActionListener(this);
		add(in);
		add(tout);
		add(send);
		setVisible(true);
	}
	public  void put(String mes){
		in.append(mes);
	}
	public void send(String mes) throws IOException{
		out.writeUTF(mes);
	}
	public void actionPerformed(ActionEvent e){
		this.put(tout.getText());
		try {
			this.send("chat,"+name+tout.getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
