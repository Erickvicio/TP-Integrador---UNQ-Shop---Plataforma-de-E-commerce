package busquedaEnCatalogo;

import catalogoDeProductos.ItemDeCatalogo;

public class PorDisponibilidad implements Criterio {

	public boolean cumple(ItemDeCatalogo catalogo) {
		return catalogo.estaDisponible(); 
	} 
}
