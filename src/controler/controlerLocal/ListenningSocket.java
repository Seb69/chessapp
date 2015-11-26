package controler.controlerLocal;

import socket.Communication;

public class ListenningSocket implements Runnable  {

	Communication communication;
	ChessGameControler chessGameControler;
	String temp;

	public  ListenningSocket (Communication communication, ChessGameControler chessGameControler)
	{
		this.communication=communication;
		this.chessGameControler=chessGameControler;
		communication.read();
		temp = communication.messageRead;
	}

	@Override
	public void run() {


		while (true)
		{
			
			//System.out.println("Listenning thread launch");
	
			 // && !communication.WRITER.equals(chessGameControler.typ
			Boolean test =  isMessageChanged( communication.messageRead);
			if (isMessageChanged( communication.messageRead)) System.out.println("TRUE");
			
			if (!(chessGameControler.type).equals(communication.messageRead) && !(chessGameControler.type).equals(null) && isMessageChanged( communication.messageRead)) 
			{
				
				System.out.println("On lance le moveRead");
				chessGameControler.moveRead();
				
			

			}
		
		}

	}

	public Boolean isMessageChanged(String messageToTest)
	{
		if(temp==null) return false;
		
		if (temp.equals(messageToTest)) 
		{
			return false;
		}
		else
		{
			temp=messageToTest;
			return true;
			
		}

	
	}
	

}
