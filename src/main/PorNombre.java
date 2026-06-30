package main;

public class PorNombre implements Criterio {
	
	private String contenido;
	
	public boolean cumple(ItemDeCatalogo itemCatalogo) {
		
		return itemCatalogo.getNombre()
                .toLowerCase()
                .contains(contenido.toLowerCase());
	}
	
	public PorNombre(String contenido) {
		this.contenido = contenido;
	}

}
