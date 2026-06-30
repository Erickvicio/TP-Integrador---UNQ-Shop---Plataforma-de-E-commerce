package main;

public class Disyuncion implements Criterio {

	private Criterio c1;
	private Criterio c2;
	
	public boolean cumple(ItemDeCatalogo catalogo) {
		return c1.cumple(catalogo) || c2.cumple(catalogo);
	}
	
	public Disyuncion(Criterio c1, Criterio c2) {
		this.c1 = c1; this.c2 = c2;
	}

}
