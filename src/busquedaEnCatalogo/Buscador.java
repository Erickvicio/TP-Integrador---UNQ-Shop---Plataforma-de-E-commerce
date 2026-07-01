package busquedaEnCatalogo;

import java.util.ArrayList;
import java.util.List;

import catalogoDeProductos.ItemDeCatalogo;

public class Buscador {

	private Criterio criterio;
	private List<ItemDeCatalogo> catalogo;
	
	public Buscador(List<ItemDeCatalogo> catalogoCompleto) {
		this.catalogo = catalogoCompleto;
	}

	public void establecerCriterio(Criterio criterio) {
		this.criterio = criterio;
	}

	public List<ItemDeCatalogo> buscar() {
			
		List<ItemDeCatalogo> resultado = new ArrayList<>();
		
		for(ItemDeCatalogo c:catalogo) {
			
			if(criterio.cumple(c)) {
				resultado.add(c);
			}
		}
		return resultado;
		
	}

}
