package busquedaCatalogo;

import main.ItemDeCatalogo;

public interface Criterio {
	
	boolean cumple(ItemDeCatalogo c);
}
