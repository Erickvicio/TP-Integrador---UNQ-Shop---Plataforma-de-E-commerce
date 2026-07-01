package busquedaEnCatalogo;

import catalogoDeProductos.ItemDeCatalogo;

public interface Criterio {
	
	boolean cumple(ItemDeCatalogo c);
}
