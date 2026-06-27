package main;

public class PorNombre implements Criterio {
	
	private String contenido;
	
	public boolean cumple(Catalogo catalogo) {
		
		return catalogo.getNombre()
                .toLowerCase()
                .contains(contenido.toLowerCase());
	}
	
	public PorNombre(String contenido) {
		this.contenido = contenido;
	}

}
