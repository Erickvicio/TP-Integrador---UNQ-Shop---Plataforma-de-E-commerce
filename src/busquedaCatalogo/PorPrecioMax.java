package busquedaCatalogo;

import main.ItemDeCatalogo;

public class PorPrecioMax implements Criterio {

	private double precioMax;
	
	public boolean cumple(ItemDeCatalogo catalogo) {
		return catalogo.precioFinal() <= precioMax;
	}

	
	public PorPrecioMax(double precio) {
		this.precioMax = precio;
	}

}
