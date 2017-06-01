package test.thread.chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class SignFrame extends Frame implements ActionListener{
	Label La1;
	Label La2;
	TextField name;
	TextField pass;
	Button sign_up;
	Button sign_in;
	ConBase con;
	Panel u;
	Panel s;
	Panel si;
	public SignFrame(){
		con = new ConBase();
		setTitle("sign");
		setLayout(new BorderLayout());
		setSize(400,110);
		u = new Panel();
		s = new Panel();
		si = new Panel();
		u.setLayout(new BorderLayout());
		s.setLayout(new BorderLayout());
		si.setLayout(new BorderLayout());
		si.setSize(100, 100);
		La1 = new Label("name:");
		La2 = new Label("pass");
		name = new TextField(30);
		pass = new TextField(30);
		sign_up = new Button("sign up");
		sign_in = new Button("sign in");
		this.addWindowListener(new WinSign(this));
		u.add(La1,BorderLayout.WEST);
		u.add(name,BorderLayout.EAST);
		s.add(La2,BorderLayout.WEST);
		s.add(pass,BorderLayout.EAST);
		si.add(sign_in,BorderLayout.WEST);
		si.add(sign_up,BorderLayout.EAST);
		add(u,BorderLayout.NORTH);
		add(s,BorderLayout.CENTER);
		add(si,BorderLayout.SOUTH);
		sign_up.addActionListener(this);
		sign_in.addActionListener(this);
		setVisible(true);
		}
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(sign_in)){
			
				try {
					if(con.search(name.getText(), pass.getText())){
						dispose();
						new frame(name.getText());
					}else{
						new PopFrame();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  
		}
		if(e.getSource().equals(sign_up)){
			
				try {
					con.insert(name.getText(), pass.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				try {
					new frame(name.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
	}
}
class WinSign extends WindowAdapter{
	Frame me;
	WinSign(Frame m){
		me = m;
	}
	public void windowClosing(WindowEvent e){
		me.dispose();
	}
}
