package test.thread.chat;

import java.io.Closeable;
import java.io.IOException;

public class closeutil {
	public static void closeAll(Closeable...io){
		for(Closeable temp:io){
			if(temp!=null){
				try {
					temp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
