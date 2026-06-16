package main;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BusquedaCatalogoTestCase {
	
	
	//SET UP
	private List<Catalogo> catalogoCompleto;
	
	private Catalogo p0;
	private Catalogo p1;
	private Catalogo p2;
	private Catalogo p3;
	private Catalogo p4;
	private Catalogo p5;
	
	private Buscador b;
	
	
	@BeforeEach
	void setUp(){
		
		//SetUp
		p0 = mock(Catalogo.class);	
		p1 = mock(Catalogo.class);
		p2 = mock(Catalogo.class);
		p3 = mock(Catalogo.class);
		p4 = mock(Catalogo.class);
		p5 = mock(Catalogo.class);
		
		//Test Double Configuration
		catalogoCompleto.add(p0);
		catalogoCompleto.add(p1);
		catalogoCompleto.add(p2);
		catalogoCompleto.add(p3);
		catalogoCompleto.add(p4);
		catalogoCompleto.add(p5);
		
		b = new Buscador(catalogoCompleto);
	}
	
	@Test
	void busquedaPorNombre() {
		
		//Test Double Configuration
		when(p5.getNombre()).thenReturn("Juego de cortinas Blackout Oferta!");
		when(p4.getNombre()).thenReturn("Zapatillas Kappa Deportivas Oferta!");
		when(p3.getNombre()).thenReturn("Pack Juego de Sabanas 2 1/2 Plazas Oferta");
		when(p2.getNombre()).thenReturn("");
		when(p1.getNombre()).thenReturn("");
		when(p0.getNombre()).thenReturn("");
		
		//Creamos un criterio por nombre (los que contengan "oferta")
		Criterio c = new PorNombre("oferta");
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<Catalogo> esperado = new ArrayList<Catalogo>();
		esperado.add(p5); esperado.add(p4); esperado.add(p3);
		
		//Exercise
		List<Catalogo> obtenido = b.buscar();
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).getNombre();
		verify(p4, times(1)).getNombre();
		verify(p3, times(1)).getNombre();
		verify(p2, times(1)).getNombre();
		verify(p1, times(1)).getNombre();
		verify(p0, times(1)).getNombre();
	}
	

	@Test
	void busquedaPorPrecio() {
		
		//Test Double Configuration
		when(p5.getPrecio()).thenReturn(22990f);
		when(p4.getPrecio()).thenReturn(13591f);
		when(p3.getPrecio()).thenReturn(25000f);
		when(p2.getPrecio()).thenReturn(109999f);
		when(p1.getPrecio()).thenReturn(89999f);
		when(p0.getPrecio()).thenReturn(59999f);
		
		//Creamos un criterio por nombre (los que contengan "oferta")
		Criterio c = new PorPrecioMax(25000f);
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<Catalogo> esperado = new ArrayList<Catalogo>();
		esperado.add(p5); esperado.add(p4); esperado.add(p3);
		
		//Exercise
		List<Catalogo> obtenido = b.buscar();
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).getPrecio();
		verify(p4, times(1)).getPrecio();
		verify(p3, times(1)).getPrecio();
		verify(p2, times(1)).getPrecio();
		verify(p1, times(1)).getPrecio();
		verify(p0, times(1)).getPrecio();
	}
	
	@Test
	void busquedaPorCategoria() {
		
		//Test Double Configuration
		when(p5.getCategoria()).thenReturn("Computacion");
		when(p4.getCategoria()).thenReturn("Computacion");
		when(p3.getCategoria()).thenReturn("Computacion");
		when(p2.getCategoria()).thenReturn("Deportes");
		when(p1.getCategoria()).thenReturn("Electronica");
		when(p0.getCategoria()).thenReturn("Cuidado Personal");
		
		//Creamos un criterio por nombre (los que contengan "oferta")
		Criterio c = new PorCategoria("Computacion");
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<Catalogo> esperado = new ArrayList<Catalogo>();
		esperado.add(p5); esperado.add(p4); esperado.add(p3);
		
		//Exercise
		List<Catalogo> obtenido = b.buscar();
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).getCategoria();
		verify(p4, times(1)).getCategoria();
		verify(p3, times(1)).getCategoria();
		verify(p2, times(1)).getCategoria();
		verify(p1, times(1)).getCategoria();
		verify(p0, times(1)).getCategoria();
	}
	
	@Test
	void busquedaPorDisponibilidad() {
		
		//Test Double Configuration
		when(p5.estaDisponible()).thenReturn(true);
		when(p4.estaDisponible()).thenReturn(true);
		when(p3.estaDisponible()).thenReturn(true);
		when(p2.estaDisponible()).thenReturn(false);
		when(p1.estaDisponible()).thenReturn(false);
		when(p0.estaDisponible()).thenReturn(false);
		
		//Creamos un criterio por nombre (los que contengan "oferta")
		Criterio c = new PorDisponibilidad();
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<Catalogo> esperado = new ArrayList<Catalogo>();
		esperado.add(p5); esperado.add(p4); esperado.add(p3);
		
		//Exercise
		List<Catalogo> obtenido = b.buscar();
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).estaDisponible();
		verify(p4, times(1)).estaDisponible();
		verify(p3, times(1)).estaDisponible();
		verify(p2, times(1)).estaDisponible();
		verify(p1, times(1)).estaDisponible();
		verify(p0, times(1)).estaDisponible();
	}
	
	
	@Test
	void busquedaConConjuncion() {
		
		
		//Test Double Configuration
		when(p5.getCategoria()).thenReturn("Electronica");
		when(p5.getPrecio()).thenReturn(9500f);
		when(p5.estaDisponible()).thenReturn(true);
		
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
				
		// Estrablecemos el resultado esperado
		List<Catalogo> esperado = new ArrayList<Catalogo>();
		esperado.add(p5);
			
		b.establecerCriterio(unCriterio);
		
		//Exercise
		List<Catalogo> obtenido = b.buscar();
			
		//Verify
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).getCategoria();
		verify(p5, times(1)).getPrecio();
		verify(p5, times(1)).estaDisponible();

	}
	
	@Test
	void busquedaConDisyuncion() {
		
		//Test Double Configuration
		when(p5.getNombre()).thenReturn("adaptador");
		when(p4.getNombre()).thenReturn("cable");
		
		/* Creamos un criterio de busqueda
		 * El nombre del producto contiene cable o adaptador
		 * */
		Criterio unCriterio = new Disyuncion(
									new PorNombre("cable"),
									new PorNombre("adaptador")
								);
		
		Set<Catalogo> esperado = new HashSet<Catalogo>();
		esperado.add(p5);
			
		b.establecerCriterio(unCriterio);
		
		//Exercise
		List<Catalogo> obtenido = b.buscar();
		
		
		//Verify
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(2)).getNombre();
		verify(p4, times(2)).getNombre();
		
	}
	
	@Test
	void busquedaConNegacion() {
		
		//Test Double Configuration
		when(p5.getCategoria()).thenReturn("Indumentaria");
		when(p4.getCategoria()).thenReturn("Electronica");
		when(p3.getCategoria()).thenReturn("Electronica");
		when(p2.getCategoria()).thenReturn("Electronica");
		when(p1.getCategoria()).thenReturn("Electronica");
		when(p0.getCategoria()).thenReturn("Electronica");
		
		/* Creamos un criterio de busqueda
		 * La categoria NO tiene que ser "Indumentaria"
		 * */
		Criterio unCriterio = new Negacion(new PorCategoria("Indumentaria"));
		
		List<Catalogo> esperado = new ArrayList<Catalogo>();
		esperado.add(p0); esperado.add(p1); esperado.add(p2);
		esperado.add(p3); esperado.add(p4);
		
		
		b.establecerCriterio(unCriterio);
		
		//Exercise
		List<Catalogo> obtenido = b.buscar();
		
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).getCategoria();
		verify(p4, times(1)).getCategoria();
		verify(p3, times(1)).getCategoria();
		verify(p2, times(1)).getCategoria();
		verify(p1, times(1)).getCategoria();
		verify(p0, times(1)).getCategoria();
	}
	

}
