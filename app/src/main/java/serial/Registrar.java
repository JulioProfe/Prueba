package serial;

import java.io.Serializable;

public class Registrar implements Serializable {

	private String name;
	
	public Registrar(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}
	

}
