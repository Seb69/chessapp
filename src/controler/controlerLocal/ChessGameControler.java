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
	public ObjectToSend objectToSend;
	
	
	String type;
	
	public ChessGameControler(ChessGame chessGame, String type) {
		
		communication = new Communication();
		objectToSend = new ObjectToSend();
		
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
		
		
		objectToSend.setCoordInit(initCoord);
		objectToSend.setCoordFinal(finalCoord);
		
		communication.write(objectToSend);
		
		if(currentchessGame.move( initCoord.x,  initCoord.y,  finalCoord.x,  finalCoord.y)) return true;
			
		return false;
	}
	
  public boolean moveAllow(Coord initCoord, Coord finalCoord)
  {
	  if(currentchessGame.moveAllow( initCoord.x,  initCoord.y,  finalCoord.x,  finalCoord.y)) return true;
	  
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
		if (SERVER.equals(type)) 
			{
			communication.runServer();
			}
		if (CLIENT.equals(type)) 
			{
			communication.runClient();
			}
		
	
		communication.read(this);
	

		
	}
	



	@Override
	public Boolean updateObservateurReception(Object object) {

		
		System.out.println("Appelle du moveRead");
		objectToSend = (ObjectToSend) object;
		
		System.out.println(objectToSend.toString());
	

	if (currentchessGame.move( objectToSend.getCoordInit().x,  objectToSend.getCoordInit().y,  objectToSend.getCoordFinal().x,  objectToSend.getCoordFinal().y)) return true;

	
		return false ;
		
		
	}




}
