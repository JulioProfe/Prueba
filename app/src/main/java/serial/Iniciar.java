package serial;

import java.io.Serializable;

public class Iniciar implements Serializable {
	
	private String name;
	private boolean iniciar=false;
	
	public Iniciar(String name, boolean iniciar) {

		this.name=name;
		this.iniciar=iniciar;
	}

	public String getName() {
		return name;
	}

	public boolean isIniciar() {
		return iniciar;
	}
	
	

}
