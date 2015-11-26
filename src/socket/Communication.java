package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication {
	
	public static ServerSocket ss = null;
	 public static Thread t;
	 
		public static Socket socket = null;
		public static Thread t1;
		
		

		private BufferedReader in = null;
		private PrintWriter out = null;

		private Thread tEmission, tReception;
		
		public String messageRead = null;
	 
public void runClient()
{
		
	try {
		
		System.out.println("Demande de connexion cote client");
		socket = new Socket("127.0.0.1",2019);
		System.out.println("Connexion établie avec le serveur"); // Si le message s'affiche c'est que je suis connecté
		
		//t1 = new Thread(new Connexion(socket));
		//t1.start();
		
	} catch (UnknownHostException e) {
	  System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
	} catch (IOException e) {
	  System.err.println("Aucun serveur à l'écoute du port "+socket.getLocalPort());
	}

	
}

public void runServer()
{
			
			try {
				
				ss = new ServerSocket(2019);
				System.out.println("Attente de la connexion client");
				socket = ss.accept();
				System.out.println("Client connecté");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
}	

public void write ()
{
	
	try {
		out = new PrintWriter(socket.getOutputStream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	Thread tEmission = new Thread(new Emission(out));
	tEmission.start();
	
}

public void read ()
{
	try {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("on est entré dans le read");
	
	Thread tReception = new Thread(new Reception(in,this));
	tReception.start();
	
	
}

}
