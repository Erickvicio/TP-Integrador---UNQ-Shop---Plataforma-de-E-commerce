package busquedaEnCatalogo;

import catalogoDeProductos.ItemDeCatalogo;

public class Conjuncion extends CriterioBinario {
	
	
	public boolean cumple(ItemDeCatalogo catalogo) {
		return c1.cumple(catalogo) && c2.cumple(catalogo);
	}
	
	public Conjuncion(Criterio c1, Criterio c2) {
		this.c1 = c1; this.c2 = c2;
	}

}
