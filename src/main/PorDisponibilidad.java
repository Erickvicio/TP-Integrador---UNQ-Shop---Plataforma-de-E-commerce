package main;

public class PorDisponibilidad implements Criterio {

	public boolean cumple(Catalogo catalogo) {
		return catalogo.estaDisponible();
	}
}
