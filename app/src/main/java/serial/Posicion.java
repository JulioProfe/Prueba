package serial;

import java.io.Serializable;

public class Posicion implements Serializable {
	
	private String name;
	private double pos;
	
	public Posicion(String name, double pos) {
		this.name=name;
		this.pos=pos;
	}

	public String getName() {
		return name;
	}

	public double getPos() {
		return pos;
	}
	
	

}
