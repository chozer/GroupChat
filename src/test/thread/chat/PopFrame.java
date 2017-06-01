package test.thread.chat;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

public class PopFrame extends Frame{
	Button err;
	//µÇÂ½´íÎóµ¯´°
	public PopFrame(){
		setTitle("Error");
		setLayout(new BorderLayout());
		this.addWindowListener(new WinError(this));
		setSize(120,120);
		err = new Button("Error!");
		add(err,BorderLayout.CENTER);
		setVisible(true);
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

