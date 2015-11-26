package controler.controlerLocal;

import socket.Communication;
import model.Coord;
import model.Couleur;
import model.Echiquier;
import model.observable.ChessGame;

public class ChessGameControler implements ChessGameControlers, Runnable {

	
	ChessGame currentchessGame;	
	public static final String SERVER = "serveur";
	public static final String CLIENT = "client";
	Communication communication;
	Thread readThread;
	
	String type;
	
	public ChessGameControler(ChessGame chessGame, String type) {
		
		communication = new Communication();
		
		this.currentchessGame = chessGame;
		this.type=type;
		
		
		
	}


	public String getMessage() {
		
		String string;
		string = currentchessGame.getMessage();
		return string;
		
	}


	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
		
		interruptThread( readThread);
		
		communication.write();
		
		//restartThread( readThread); 
		
		if(currentchessGame.move( initCoord.x,  initCoord.y,  finalCoord.x,  finalCoord.y)) return true;
			
		return false;
	}
	

	public boolean moveRead() {
		
		
		
	System.out.println("On est dans le moveRead");
		
		return false;
	}


	@Override
	public boolean isEnd() {
		
		return currentchessGame.isEnd();

	}

	@Override
	public Couleur getColorCurrentPlayer() {
	
		return currentchessGame.getColorCurrentPlayer();
	}

	@Override
	public void run() {
		
		
		// TODO Auto-generated method stub
		if (SERVER.equals(type)) communication.runServer();
		if (CLIENT.equals(type)) communication.runClient();

		readThread = new Thread(new ListenningSocket(communication,this));
		readThread.start();
		
	}
	
	public void interruptThread(Thread thread)
	{
		thread.interrupt();
		while (!thread.isInterrupted())
		{
			//On fait rien
		}
		
	}
	public void restartThread(Thread thread)
	{
		thread.start();
		
	}

}
