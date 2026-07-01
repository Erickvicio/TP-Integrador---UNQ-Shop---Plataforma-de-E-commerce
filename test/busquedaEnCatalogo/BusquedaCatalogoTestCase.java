package main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Caja;
import main.ItemDeCatalogo;
import main.ItemDeCatalogo;
import main.Producto;

class BusquedaCatalogoTestCase {
	
	
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
		
		 // Productos individuales

	    p0 = new Producto(
	            "Notebook Lenovo IdeaPad",
	            "Notebook Lenovo Ryzen 5",
	            0,
	            1000,
	            "Lenovo",
	            "Electronica",
	            89999,
	            2.2
	    );

	    p1 = new Producto(
	            "Juego de cortinas Blackout Oferta!",
	            "Cortinas para dormitorio",
	            15,
	            1001,
	            "Genérica",
	            "Hogar",
	            22990,
	            3
	    );

	    p2 = new Producto(
	            "Zapatillas Kappa Deportivas Oferta!",
	            "Zapatillas deportivas",
	            10,
	            1002,
	            "Kappa",
	            "Deportes",
	            13591,
	            1
	    );

	    Producto adaptador = new Producto(
	            "Adaptador USB-C",
	            "Adaptador USB tipo C",
	            0,
	            1003,
	            "Samsung",
	            "Electronica",
	            8000,
	            0.1
	    );

	    Producto cable = new Producto(
	            "Cable HDMI 2 metros",
	            "Cable HDMI alta velocidad",
	            0,
	            1004,
	            "Philips",
	            "Electronica",
	            9500,
	            0.3
	    );

	    // Caja de hogar

	    Caja cajaOferta = new Caja(
	            "Caja Oferta Hogar",
	            "Caja promocional para el hogar",
	            20,
	            "Hogar"
	    );

	    cajaOferta.agregarItem(p1, 1);
	    cajaOferta.agregarItem(p2, 1);

	    p3 = cajaOferta;

	    // Caja de electrónica

	    Caja comboElectronica = new Caja(
	            "Combo Electronica",
	            "Caja con accesorios electrónicos",
	            10,
	            "Electronica"
	    );

	    comboElectronica.agregarItem(adaptador, 2);
	    comboElectronica.agregarItem(cable, 1);

	    p4 = comboElectronica;

	    // Producto individual

	    p5 = adaptador;
	    
	    //Aumentamos el stock de todos los productos y cajas instanciadas
	    p0.incrementarStock(10);
	    p1.incrementarStock(10);
	    p2.incrementarStock(10);
	    p3.incrementarStock(10);
	    p4.incrementarStock(10);
	    p5.incrementarStock(10);
	    
	    
	    // Añadimos los productos instanciados al catalogo con el que vamos a trabajar
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
		
		
		//Creamos un criterio por nombre (los que contengan "oferta")
		Criterio c = new PorNombre("oferta");
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p1); esperado.add(p2); esperado.add(p3);
		
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));
		
		//Busqueda por "Adaptador"
		
		Criterio c1 = new PorNombre("Adaptador");
		
		b.establecerCriterio(c1);
		
		esperado.clear();
		obtenido.clear();
		
		esperado.add(p5);
		obtenido = b.buscar();
		
		Assertions.assertTrue(obtenido.containsAll(esperado));	
	}
	

	@Test
	void busquedaPorPrecio() {
		
		//Creamos un criterio por precio maximo
		Criterio c = new PorPrecioMax(25000f);
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p1); // 22990
		esperado.add(p2); // 13591
		esperado.add(p5); // 8000
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));
	}
	
	@Test
	void busquedaPorCategoria() {
		
		//Creamos un criterio por nombre (los que contengan "oferta")
		Criterio c = new PorCategoria("Electronica");
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p0);
		esperado.add(p4);
		esperado.add(p5);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));

	}
	
	@Test
	void busquedaPorDisponibilidad() {
		
		//Test Double Configuration
		
		//Creamos un criterio por nombre (los que contengan "oferta")
		Criterio c = new PorDisponibilidad();
		
		//Establecemos el criterio
		b.establecerCriterio(c);
		
		//Establecemos los que queremos encontrar
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>(catalogoCompleto);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
//		System.out.print(esperado);
//		System.out.print(obtenido);
		
		//Verify
		//Comprobamos que el resultado contenga todo lo que se esperaba
		Assertions.assertTrue(obtenido.containsAll(esperado));
	}
	
	
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
				
		// Estrablecemos el resultado esperado
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p5);
			
		b.establecerCriterio(unCriterio);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
			
		//Verify
		Assertions.assertTrue(obtenido.containsAll(esperado));

	}
	
	@Test
	void busquedaConDisyuncion() {
		
		//Test Double Configuration
		
		/* Creamos un criterio de busqueda
		 * El nombre del producto contiene cable o adaptador
		 * */
		Criterio unCriterio = new Disyuncion(
									new PorNombre("combo"),
									new PorNombre("adaptador")
								);
		
		Set<ItemDeCatalogo> esperado = new HashSet<ItemDeCatalogo>();
		esperado.add(p5); //Combo electronica
		esperado.add(p4); // adaptador usb-c
			
		b.establecerCriterio(unCriterio);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
		
		//Verify
		Assertions.assertTrue(obtenido.containsAll(esperado));
		
	}
	
	@Test
	void busquedaConNegacion() {
		

		/* Creamos un criterio de busqueda
		 * La categoria NO tiene que ser "Indumentaria"
		 * */
		Criterio unCriterio = new Negacion(new PorCategoria("Hogar"));
		
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p0); // Electronica
	    esperado.add(p2); // Deportes
	    esperado.add(p4); // Electronica
	    esperado.add(p5); // Electronica
		
		
		b.establecerCriterio(unCriterio);
		
		//Exercise
		List<ItemDeCatalogo> obtenido = b.buscar();
		
		Assertions.assertTrue(obtenido.containsAll(esperado));

	}

}
