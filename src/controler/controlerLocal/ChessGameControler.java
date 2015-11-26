package controler.controlerLocal;

import java.util.List;


import observateur.ObservateurReception;
import socket.Communication;
import model.Coord;
import model.Couleur;
import model.Echiquier;
import model.PieceIHM;
import model.observable.ChessGame;

public class ChessGameControler  implements ChessGameControlers, Runnable, ObservateurReception {

	
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
		

		
		communication.write(type);
		

		
		if(currentchessGame.move( initCoord.x,  initCoord.y,  finalCoord.x,  finalCoord.y)) return true;
			
		return false;
	}
	

	public boolean moveRead() {
		
		
		
	System.out.println("Appelle du moveRead");
	
		
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
		
		addObservateurReception();

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


	@Override
	public void updateObservateur() {
		// TODO Auto-generated method stub
		moveRead();
		
		
	}




}
