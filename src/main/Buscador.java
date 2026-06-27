package main;

import java.util.ArrayList;
import java.util.List;

public class Buscador {

	private Criterio criterio;
	private List<Catalogo> catalogo;
	
	public Buscador(List<Catalogo> catalogo) {
		this.catalogo = catalogo;
	}

	public void establecerCriterio(Criterio criterio) {
		this.criterio = criterio;
	}

	public List<Catalogo> buscar() {
			
		List<Catalogo> resultado = new ArrayList<>();
		
		for(Catalogo c:catalogo) {
			
			if(criterio.cumple(c)) {
				resultado.add(c);
			}
		}
		return resultado;
		
	}

}
