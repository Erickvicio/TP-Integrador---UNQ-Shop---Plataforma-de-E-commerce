package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import busquedaEnCatalogo.*;
import cicloDeVidaPedido.*;
import metodoDePago.*;
import metodosDeEnvio.*;
import catalogoDeProductos.*;
import static org.mockito.Mockito.*;



class SystemTestCase {

	// Lista que contiene todos los items de un catalogo
	private ArrayList<ItemDeCatalogo> unCatalogo = new ArrayList<>();
	
	// Items de un catalogo (Tanto cajas como productos)
	private Caja p0;
	private Producto p1;
	private Producto p2;
	private Producto p3;
	private Producto p4;
	private Producto p5;
	
	// Pedido que contiene unos items del catalogo
	private Pedido unPedido;
	
	// Buscador que filtra por un criterio la lista de items de catalogo
	private Buscador buscador;
	
	// Un gestor de reportes que sirve para comunicarse con el punto 2.5 Reporte (Visitor)
	private BaseDeReportes barep;
	
	// La direccion del envio
	private Direccion dir;
	
//	public Caja(String nombre,String descripcion,int descuento, String categoria)
	@BeforeEach
	void setUp() {
		
		// Instanciar una caja
		p0 = new Caja(
				"Pack Gamer",
				"Un pack con articulos gamers",
				0,
				"Periféricos"
				);
		// Settearle el Stock
		p0.incrementarStock(10);
		
		
		p1 = new Producto(
			    "Mouse Inalámbrico",
			    "Mouse ergonómico con conexión Bluetooth",
			    10,
			    1001,
			    "Logitech",
			    "Periféricos",
			    24999.99,
			    0.12
			);
		p1.incrementarStock(152);

		p2 = new Producto(
			    "Teclado Mecánico",
			    "Teclado mecánico con switches rojos y retroiluminación RGB",
			    15,
			    1002,
			    "Redragon",
			    "Periféricos",
			    68999.50,
			    0.95
			);

		p2.incrementarStock(30);

		p3 = new Producto(
			    "Monitor 24\"",
			    "Monitor Full HD IPS de 24 pulgadas",
			    5,
			    1003,
			    "Samsung",
			    "Monitores",
			    289999.00,
			    3.80
			);
		p3.incrementarStock(6);

		p4 = new Producto(
			    "Auriculares Gamer",
			    "Auriculares con micrófono y sonido envolvente 7.1",
			    20,
			    1004,
			    "HyperX",
			    "Audio",
			    30000.90,
			    0.35
			);
		p4.incrementarStock(500);

		p5 = new Producto(
			    "Disco SSD 1TB",
			    "Unidad de estado sólido NVMe PCIe Gen4",
			    12,
			    1005,
			    "Kingston",
			    "Almacenamiento",
			    129999.99,
			    0.08
			);
		p5.incrementarStock(154);
		
		
		
		p0.agregarItem(p1, 3);
		p0.agregarItem(p2, 3);
		
		// Agregamos los items de catalogo instanciados a el Catalogo general
		unCatalogo.add(p0);
		unCatalogo.add(p1);
		unCatalogo.add(p2);
		unCatalogo.add(p3);
		unCatalogo.add(p4);
		unCatalogo.add(p5);
		
		
		// Inicializamos la Base de reportes y la direccion
		barep = new BaseDeReportes();
		dir = mock(Direccion.class);
		
		
		//Creamos un pedido sin items de momento
		unPedido = new Pedido("hernandezsosa45@yahooo.com.ar", dir, barep);
		
		//Establecemos la lista del buscador
		buscador = new Buscador(unCatalogo);
		
	}
	
	@Test
	void test1() {
	
	// Paso 1 (Filtrar de la lista de los items con precio max)
		
		//Establecemos un critero para buscar un producto
		Criterio masBarato = new PorPrecioMax(30000);
		
		// Settear al buscador
		buscador.establecerCriterio(masBarato);
		
		// Me quedo con el resultado
		List<ItemDeCatalogo> catalogoResultante = buscador.buscar();
		// RESULTADO -> p4, p1
		
		// Checkeamos que el catalogo resultante contenga los elementos esperados (Verify)
		List<ItemDeCatalogo> esperado = new ArrayList<ItemDeCatalogo>();
		esperado.add(p1); esperado.add(p4);
		Assertions.assertTrue(catalogoResultante.containsAll(esperado));
		
	// Paso 2 ( Armamos el pedido)
		
		// aca el pedido esta en estado Borrador y le agregagamos cosas
		// Agregamos los items que encontramos en la busqueda
		// OBS: esto podria ser una funcion dentro pedido??
		for(ItemDeCatalogo item:catalogoResultante) {
			unPedido.agregarItem(item);
		}
		// RESULTADO -> pedido contiene p4 y p1
		
		// Verificamos que el pedido contenga los items agregados, 1 item de cada uno
		
//		System.out.print(unPedido.getCarrito().getDetallePedidos().get(0));
//		System.out.print(unPedido.getCarrito().getDetallePedidos().get(1));
		
		// Comprobacion de que cada detallepedido del pedido contiene los items p4 y p1
		assertTrue(unPedido.getCarrito().getDetallePedidos().get(0).getItem().getNombre().equals(p1.getNombre()));
		assertTrue(unPedido.getCarrito().getDetallePedidos().get(0).getCantidad() == 1);
		assertTrue(unPedido.getCarrito().getDetallePedidos().get(1).getItem().getNombre().equals(p4.getNombre()));
		assertTrue(unPedido.getCarrito().getDetallePedidos().get(1).getCantidad() == 1);
		
	// Paso 3 Configuramos el Metodo de envio
		
		//Configuracion de la plataforma de correos que utiliza
		CorreoArgentino apiEnvioEstandar = mock(CorreoArgentino.class);
		
		// Instanciamos el tipo de envio del pedido
		Estandar tipoEnvio = new Estandar(apiEnvioEstandar);
		
		// Establecemos el tipo de envio del pedido
		unPedido.setTipoDeEnvio(tipoEnvio);
		
	// Paso 4 Configuramos el Metodo de pago
		
		ApiTarjetaDeCredito apiTarjeta = mock(ApiTarjetaDeCredito.class);
		
		TarjetaDeCredito tarjetaBBVA = new TarjetaDeCredito(apiTarjeta);
		
		unPedido.setMetodoDePago(tarjetaBBVA);
		
	// Paso 5 Ejecutar el ciclo de vida del Pedido
		//aca el pedido va a pasar a confirmado
		unPedido.siguiente();
		assertEquals(p1.getStock(),p1.getStock()-1);
		assertEquals(p4.getStock(),p4.getStock()-1);
		
				
		/*
		 * Testear que al confirmarse el pedido se interactua con las Api
		 * y que el stock global de los productos dentro del pedido decremente
		 * */	
		
	// Paso 6 pedido pasa de Confirmado a En_Preparacion
		unPedido.siguiente();
		
	// paso 7 pedido pasa de En_Preparacion a Enviado
		unPedido.siguiente();
		
	// paso 8 pedido pasa de Enviado a Entregado
		unPedido.siguiente();
	}

}