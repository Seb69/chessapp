package socket;
import java.io.BufferedReader;
import java.io.IOException;


public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null;
	Communication communication;
	
	public Reception(BufferedReader in, Communication communication){
		
		this.in = in;
		this.communication=communication;
	}
	
	public void run() {
		System.out.println("on est entré dans Reception");
		
		while(true){
	        try {
	        	
			message = in.readLine();
			
			if (!("".equals(message))) System.out.println("on ecrit dans messageRead de l'object communication");;
			
			if (!("".equals(message))) communication.messageRead = message;
			
			System.out.println(message);
			
			
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
