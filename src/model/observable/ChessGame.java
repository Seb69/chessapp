package model.observable;

import observable.Observable;
import observateur.Observateur;
import model.Couleur;
import model.Echiquier;

import java.util.List;

public class ChessGame extends Observable {
	
	Echiquier currentechiquier;
	
	//-----------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------
	public ChessGame(){	
		this.currentechiquier = new Echiquier ();
	}
	//-----------------------------------------------------------------------------------------
	public String toString() {
		String string;
		
		string = currentechiquier.toString() + "\n" ;
		string = currentechiquier.getMessage();
		
		return string;
	}
	//-----------------------------------------------------------------------------------------
	public boolean move (int xInit, int yInit, int xFinal, int yFinal) {
		
		if (!currentechiquier.isMoveOk( xInit,  yInit,  xFinal,  yFinal)) return false;
		if (!currentechiquier.isMoveLegal( xInit,  yInit,  xFinal,  yFinal)) return false;
		if (!currentechiquier.move ( xInit,  yInit,  xFinal,  yFinal)) return false ;
		
		notifyObservable(currentechiquier.getPiecesIHM());
		currentechiquier.switchJoueur();
		
		return true;
	} 
	//-----------------------------------------------------------------------------------------
	public boolean isEnd() {
		
		return currentechiquier.isEnd();
		
	}
	//-----------------------------------------------------------------------------------------
	public String getMessage() {
		
		return currentechiquier.getMessage();
		
	}
	//-----------------------------------------------------------------------------------------
	public Couleur getColorCurrentPlayer() {
		
		return currentechiquier.getColorCurrentPlayer();
	}



}
