package main;

public class PorCategoria implements Criterio {
	
	private String categoria;

	public boolean cumple(Catalogo catalogo) {
		return catalogo.getCategoria().equals(categoria);
	}
	
	public PorCategoria(String categoria) {
		this.categoria = categoria;
	}

}
