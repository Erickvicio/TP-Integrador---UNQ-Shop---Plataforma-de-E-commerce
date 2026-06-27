package main;

public class Negacion implements Criterio {

	private Criterio c1;
	
	public boolean cumple(Catalogo catalogo) {
		return !c1.cumple(catalogo);
	}
	
	public Negacion(Criterio c1) {
		this.c1 = c1;
	}

}
