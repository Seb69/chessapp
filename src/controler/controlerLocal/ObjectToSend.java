package controler.controlerLocal;

import java.io.Serializable;

import model.Coord;

public class ObjectToSend implements Serializable {
	private Coord coordInit,coordFinal;

	public Coord getCoordInit() {
		return coordInit;
	}


	public void setCoordInit(Coord coordInit) {
		this.coordInit = coordInit;
	}

	public Coord getCoordFinal() {
		return coordFinal;
	}

	public void setCoordFinal(Coord coordFinal) {
		this.coordFinal = coordFinal;
	}

	@Override
	public String toString() {
		return "ObjectToSend [coordInit=" + coordInit + ", coordFinal="
				+ coordFinal + "]";
	}

}
