package busquedaCatalogo;

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

import main.ItemDeCatalogo;

class BusquedaCatalogoTestMockCase {
	
	
	//SET UP
	private List<ItemDeCatalogo> catalogoCompleto = new ArrayList<>();
	
	private ItemDeCatalogo p0;
	private ItemDeCatalogo p1;
	private ItemDeCatalogo p2;
	private ItemDeCatalogo p3;
	private ItemDeCatalogo p4;
	private ItemDeCatalogo p5;
	
	private Buscador b;
	
	
	@BeforeEach
	void setUp(){
		
		//SetUp
		p0 = mock(ItemDeCatalogo.class);	
		p1 = mock(ItemDeCatalogo.class);
		p2 = mock(ItemDeCatalogo.class);
		p3 = mock(ItemDeCatalogo.class);
		p4 = mock(ItemDeCatalogo.class);
		p5 = mock(ItemDeCatalogo.class);
		
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
		when(p2.getNombre()).thenReturn("1");
		when(p1.getNombre()).thenReturn("2");
		when(p0.getNombre()).thenReturn("3");
		
		//Creamos un criterio por nombre (los que contengan "oferta")
		Criterio c = new PorNombre("oferta");
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p5); esperado.add(p4); esperado.add(p3);
		
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).getNombre();
		verify(p4, times(1)).getNombre();
		verify(p3, times(1)).getNombre();
		verify(p2, times(1)).getNombre();
		verify(p1, times(1)).getNombre();
		verify(p0, times(1)).getNombre();
		
		
//		System.out.println(esperado);
//		System.out.println(obtenido);
		
	}
	

	@Test
	void busquedaPorPrecio() {
		
		//Test Double Configuration
		when(p5.precioFinal()).thenReturn((double)22990f);
		when(p4.precioFinal()).thenReturn((double)13591f);
		when(p3.precioFinal()).thenReturn((double)25000f);
		when(p2.precioFinal()).thenReturn((double)109999f);
		when(p1.precioFinal()).thenReturn((double)89999f);
		when(p0.precioFinal()).thenReturn((double)59999f);
		
		//Creamos un criterio por nombre (los que contengan "oferta")
		Criterio c = new PorPrecioMax(25000f);
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p5); esperado.add(p4); esperado.add(p3);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).precioFinal();
		verify(p4, times(1)).precioFinal();
		verify(p3, times(1)).precioFinal();
		verify(p2, times(1)).precioFinal();
		verify(p1, times(1)).precioFinal();
		verify(p0, times(1)).precioFinal();
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
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p5); esperado.add(p4); esperado.add(p3);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
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
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p5); esperado.add(p4); esperado.add(p3);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
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
		when(p5.precioFinal()).thenReturn((double) 9500f);
		when(p5.estaDisponible()).thenReturn(true);
		
		when(p4.getCategoria()).thenReturn("Electronica");
		when(p4.precioFinal()).thenReturn((double)10001f);
		when(p4.estaDisponible()).thenReturn(true);
		
		when(p3.getCategoria()).thenReturn("Electrodomestico");
		when(p3.precioFinal()).thenReturn((double)9500f);
		when(p3.estaDisponible()).thenReturn(true);
		
		when(p2.getCategoria()).thenReturn("Cuidado Personal");
		when(p2.precioFinal()).thenReturn((double)9500f);
		when(p2.estaDisponible()).thenReturn(false);
		
		when(p1.getCategoria()).thenReturn("Electronica");
		when(p1.precioFinal()).thenReturn((double)250000f);
		when(p1.estaDisponible()).thenReturn(true);
		
		when(p0.getCategoria()).thenReturn("Automotor");
		when(p0.precioFinal()).thenReturn((double)9500f);
		when(p0.estaDisponible()).thenReturn(false);
		
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
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p5);
			
		b.establecerCriterio(unCriterio);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
			
		//Verify
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).getCategoria();
		verify(p5, times(1)).precioFinal();
		verify(p5, times(1)).estaDisponible();

	}
	
	@Test
	void busquedaConDisyuncion() {
		
		//Test Double Configuration
		when(p5.getNombre()).thenReturn("adaptador");
		when(p4.getNombre()).thenReturn("cable");
		when(p3.getNombre()).thenReturn("figurita");
		when(p2.getNombre()).thenReturn("celular");
		when(p1.getNombre()).thenReturn("Guantes");
		when(p0.getNombre()).thenReturn("Corchon");
		
		/* Creamos un criterio de busqueda
		 * El nombre del producto contiene cable o adaptador
		 * */
		Criterio unCriterio = new Disyuncion(
									new PorNombre("cable"),
									new PorNombre("adaptador")
								);
		
		Set<ItemDeCatalogo> esperado = new HashSet<ItemDeCatalogo>();
		esperado.add(p5); esperado.add(p4);
			
		b.establecerCriterio(unCriterio);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
		
		//Verify
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(2)).getNombre();
		verify(p4, times(1)).getNombre(); // Solo se accede una vez ya que la primera de las cond era si contiene "cable"
		verify(p3, times(2)).getNombre();	
		verify(p2, times(2)).getNombre();
		verify(p1, times(2)).getNombre();
		verify(p0, times(2)).getNombre();
		
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
		
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p0); esperado.add(p1); esperado.add(p2);
		esperado.add(p3); esperado.add(p4);
		
		
		b.establecerCriterio(unCriterio);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
		Assertions.assertTrue(obtenido.containsAll(esperado));
		verify(p5, times(1)).getCategoria();
		verify(p4, times(1)).getCategoria();
		verify(p3, times(1)).getCategoria();
		verify(p2, times(1)).getCategoria();
		verify(p1, times(1)).getCategoria();
		verify(p0, times(1)).getCategoria();
	}
	

}
