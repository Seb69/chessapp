package observable;

import java.util.ArrayList;
import java.util.Iterator;

import observateur.Observateur;

public class Observable {
	
	ArrayList<Observateur> observeurlist = new ArrayList<Observateur>();
	
public void notifyObservable()
{
	for (Observateur obs : observeurlist)
	{
		obs.updateObservateur();
	}
}

public void addObservateur(Observateur observateur)
		{		
		observeurlist.add(observateur);	
		}
}
