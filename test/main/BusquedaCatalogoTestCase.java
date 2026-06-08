package main;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BusquedaCatalogoTestCase {
	
	
	//SET UP
	
	private List<Catalogo> catalogoCompleto;
	private Buscador b = new Buscador(catalogoCompleto);
	

	
	
	@Test
	void busquedaConConjuncion() {
		
		
		/* Creamos un criterio de busqueda
		 * Categoria debe ser "Electronica", el precio debe ser menor o igual a $10.000
		 * y debe estar disponible
		 * */
		Criterio unCriterio = 
				new Conjuncion(		//Conjuncion operador binario
					new Conjuncion(
						new PorCategoria("Electronica"),
						new PorPrecioMax(10000)
					),				
					new PorDisponibilidad()
				);
				
		List<Catalogo> resultadoEsperado = null;
				
		b.establecerCriterio(unCriterio);
		
		List<Catalogo> resultadoObtenido = b.buscar();
			
		
		Assertions.assertTrue(resultadoObtenido.containsAll(resultadoEsperado));
	}
	
	@Test
	void busquedaConDisyuncion() {
		
		
		/* Creamos un criterio de busqueda
		 * El nombre del producto contiene cable o adaptador
		 * */
		Criterio unCriterio = new Disyuncion(
									new PorNombre("cable"),
									new PorNombre("adaptador")
								);
		
		List<Catalogo> resultadoEsperado = null;
			
		b.establecerCriterio(unCriterio);
		
		List<Catalogo> resultadoObtenido = b.buscar();
		
		Assertions.assertTrue(resultadoObtenido.containsAll(resultadoEsperado));
		
	}
	
	@Test
	void busquedaConNegacion() {
		
		
		/* Creamos un criterio de busqueda
		 * La categoria NO tiene que ser "Indumentaria"
		 * */
		Criterio unCriterio = new Negacion(new PorCategoria("Indumentaria"));
		
		List<Catalogo> resultadoEsperado = null;
		
		b.establecerCriterio(unCriterio);
				
		List<Catalogo> resultadoObtenido = b.buscar();
		
		Assertions.assertTrue(resultadoObtenido.containsAll(resultadoEsperado));
	}
	

}
