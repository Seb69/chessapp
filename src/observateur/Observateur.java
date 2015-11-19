package observateur;

import java.util.List;

import model.PieceIHM;

public interface Observateur {
	
	public void updateObservateur(List<PieceIHM> chessPieceList);

}
