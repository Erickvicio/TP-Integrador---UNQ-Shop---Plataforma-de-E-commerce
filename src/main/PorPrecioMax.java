package main;

public class PorPrecioMax implements Criterio {

	public boolean cumple(Catalogo catalogo) {
		return false;
	}

	private double precio;
	
	public PorPrecioMax(double precio) {
		this.precio = precio;
	}

}
