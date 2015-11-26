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
			if (isMessageChanged(communication.messageRead)) 
			{
				System.out.println("Message recu");
				chessGameControler.moveRead();
				

			}
		}

	}

	public Boolean isMessageChanged(String messageToTest)
	{
		if (messageToTest!=temp) 
		{
			temp=communication.messageRead;
			return true;
		}

		return false;
	}

}
