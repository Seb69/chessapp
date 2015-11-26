package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Emission implements Runnable {

	private PrintWriter out;
	private String messageToSend = null;

	
	public Emission(PrintWriter out,String messageToSend) {
		this.out = out;
		this.messageToSend=messageToSend;
	}

	
	public void run() {
		
		
		  
		  while(true){
			  System.out.println("Emission : " + messageToSend);
		
				out.println(messageToSend);
			    out.flush();
			    
			    try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    break;
			  }
	}
}
