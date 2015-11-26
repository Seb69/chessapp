package controler.controlerLocal;

import socket.Communication;

public class ListenningSocket implements Runnable  {

	Communication communication;
	
	public  ListenningSocket (Communication communication)
	{
		this.communication=communication;
	}
	
	@Override
	public void run() {
		
		communication.read();
	}

	
}
