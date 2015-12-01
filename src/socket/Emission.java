package socket;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.Socket;

public class Emission implements Runnable {


	private Object objectToSend ;
	public Socket socket = null;
	private ObjectOutputStream out;
	
	public Emission(Socket socket,Object objectToSend) {
		this.socket = socket;
		this.objectToSend=objectToSend;
	}

	
	public void run() {
		
		
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		  
		  while(true){
			
			 
			  
			    try {
			    	
			    	if (objectToSend!=null)
			    	{
			    	
			    
					out.writeObject(objectToSend);
					out.flush();
					
					
					System.out.println("Emission : " + objectToSend.toString());
			    	}
						
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    
				
			    
			  
			    break;
			  }
	}
}
