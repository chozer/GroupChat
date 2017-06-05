package chat.cilent.feather;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;


public class SignFrame extends Frame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Label La1;
	Label La2;
	TextField name;
	TextField pass;
	Button sign_up;
	Button sign_in;
	Panel u;
	Panel s;
	Panel si;
	
	Socket cilent;
	ConNet connet;
	LogDevice log;
	
	public SignFrame() throws IOException{
		
		connet = new ConNet();
		cilent = connet.Connection();
		log = new LogDevice(cilent);
		
		setTitle("sign");
		setLayout(new BorderLayout());
		setSize(300,110);
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
		//登陆
		if(e.getSource().equals(sign_in)){
			
				try {
					if(log.login(name.getText(), pass.getText())){
						if(!log.log()){
							dispose();
							//用户界面
							new frame(name.getText(),cilent);
						}else{
							new PopFrame(true);
						}
					}else{
						new PopFrame();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  
		}
		//注册
		if(e.getSource().equals(sign_up)){
			
				try {
					//检测用户是否存在
					if(!log.register(name.getText(), pass.getText())){
						new PopFrame(name.getText());
					}
					else{
						//注册成功
						dispose();
						//登陆到用户界面
						new frame(name.getText(),cilent);
					}
				}  catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
	}
	//默认关闭窗口
class WinSign extends WindowAdapter{
	Frame me;
	WinSign(Frame m){
		me = m;
	}
	public void windowClosing(WindowEvent e){
		me.dispose();
		System.exit(0);
	}
}
}

