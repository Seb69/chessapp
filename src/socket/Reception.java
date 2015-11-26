package socket;
import java.io.BufferedReader;
import java.io.IOException;

import observable.ObservableReception;




public class Reception extends ObservableReception implements Runnable {

	private BufferedReader in;
	private String message = null;
	Communication communication;
	
	public Reception(BufferedReader in, Communication communication){
		
		this.in = in;
		this.communication=communication;
	}
	
	public void run() {
		
		
		while(true){
	        try {
	        	
			message = in.readLine();
			
		    try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (!("".equals(message))) System.out.println("Reception : " + message);
			
			if (!("".equals(message))) notifyObservableReception();
			
			
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
