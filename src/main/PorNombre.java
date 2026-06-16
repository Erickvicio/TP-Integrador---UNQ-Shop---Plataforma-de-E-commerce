package main;

public class PorNombre implements Criterio {

	public boolean cumple(Catalogo catalogo) {
		return false;
	}
	
	private String contenido;

	public PorNombre(String contenido) {
		this.contenido = contenido;
	}

}
