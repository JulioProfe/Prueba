package serial;

import java.io.Serializable;

public class Select implements Serializable {

	private String name;
	private int shoe;
	
	public Select(String name, int shoe) {
		
		this.name=name;
		this.shoe=shoe;
	}

	public String getName() {
		return name;
	}

	public int getShoe() {
		return shoe;
	}
	

}
