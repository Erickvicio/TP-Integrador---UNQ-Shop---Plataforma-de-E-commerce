package main;

public class PorCategoria implements Criterio {
	
	private String categoria;

	public boolean cumple(Catalogo catalogo) {
		return false;
	}
	
	public PorCategoria(String categoria) {
		this.categoria = categoria;
	}

}
