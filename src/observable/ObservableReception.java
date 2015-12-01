package observable;

import java.util.ArrayList;
import observateur.ObservateurReception;

public abstract class ObservableReception {
	
	ArrayList<ObservateurReception> observeurlist = new ArrayList<ObservateurReception>();
	
	public void notifyObservableReception(Object object)
	{

		
		for (ObservateurReception obs : observeurlist)
		{
			obs.updateObservateurReception(object);
		}
	}

	public void addObservateurReception(ObservateurReception observateurreception)
			{		
			observeurlist.add(observateurreception);	
			}

}
