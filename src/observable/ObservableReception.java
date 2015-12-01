package observable;

import java.util.ArrayList;
import java.util.List;

import model.PieceIHM;
import observateur.Observateur;
import observateur.ObservateurReception;

public abstract class ObservableReception {
	
	ArrayList<ObservateurReception> observeurlist = new ArrayList<ObservateurReception>();
	
	public void notifyObservableReception()
	{

		
		for (ObservateurReception obs : observeurlist)
		{
			obs.updateObservateurReception();
		}
	}

	public void addObservateurReception(ObservateurReception observateurreception)
			{		
			observeurlist.add(observateurreception);	
			}

}
