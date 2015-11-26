package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Emission implements Runnable {

	private PrintWriter out;
	private String message = null;

	
	public Emission(PrintWriter out) {
		this.out = out;
	}

	
	public void run() {
		
		
		  
		  while(true){
				message = "On ecrit dans la socket";
				out.println(message);
			    out.flush();
			  }
	}
}
