package chat.cilent.feather;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopFrame extends Frame{
	Button err;
	//错误信息弹窗
	public PopFrame(){
		setTitle("Error!");
		setLayout(new BorderLayout());
		this.addWindowListener(new WinError(this));
		setSize(120,120);
		err = new Button("账户信息错误！1");
		add(err,BorderLayout.CENTER);
		setVisible(true);
	}
	public PopFrame(String name){
		setTitle("Error!");
		setLayout(new BorderLayout());
		this.addWindowListener(new WinError(this));
		setSize(120,120);
		err = new Button(name+"已存在！2");
		add(err,BorderLayout.CENTER);
		setVisible(true);
	}
	public PopFrame(boolean a){
		if(a){
			setTitle("Error!");
			setLayout(new BorderLayout());
			this.addWindowListener(new WinError(this));
			setSize(120,120);
			err = new Button("账户已登陆！3");
			add(err,BorderLayout.CENTER);
			setVisible(true);
		}
	}
}
class WinError extends WindowAdapter{
	PopFrame me;
	WinError(PopFrame m){
		me = m;
	}
	public void windowClosing(WindowEvent e){
		me.dispose();
	}
}

