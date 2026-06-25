package main;

public class PorPrecioMax implements Criterio {

	private double precioMax;
	
	public boolean cumple(Catalogo catalogo) {
		return catalogo.getPrecio() <= precioMax;
	}

	
	public PorPrecioMax(double precio) {
		this.precioMax = precio;
	}

}
