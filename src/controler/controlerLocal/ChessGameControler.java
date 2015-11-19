package controler.controlerLocal;

import model.Coord;
import model.Couleur;
import model.Echiquier;
import model.observable.ChessGame;

public class ChessGameControler implements ChessGameControlers {

	
	ChessGame currentchessGame;	
	
	public ChessGameControler(ChessGame chessGame) {
		
		this.currentchessGame = chessGame;
		
	}

	public String getMessage() {
		
		String string;
		string = currentchessGame.getMessage();
		return string;
		
	}


	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
		
		if(currentchessGame.move( initCoord.x,  initCoord.y,  finalCoord.x,  finalCoord.y)) return true;
			
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

}
