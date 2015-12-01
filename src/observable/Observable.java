package observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.PieceIHM;
import observateur.Observateur;

public abstract class Observable {

	ArrayList<Observateur> observeurlist = new ArrayList<Observateur>();

	public void notifyObservable(List<PieceIHM> ChessPieceList)
	{
		for (Observateur obs : observeurlist)
		{
			obs.updateObservateur(ChessPieceList);
		}
	}

	public void addObservateur(Observateur observateur)
	{		
		observeurlist.add(observateur);	
	}
}
