package busquedaCatalogo;

import main.ItemDeCatalogo;

public class PorDisponibilidad implements Criterio {

	public boolean cumple(ItemDeCatalogo catalogo) {
		return catalogo.estaDisponible();
	}
}
