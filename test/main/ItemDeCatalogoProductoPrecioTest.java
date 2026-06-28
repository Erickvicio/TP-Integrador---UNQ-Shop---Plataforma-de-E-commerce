package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Producto;

class ItemDeCatalogoProductoPrecioTest {
	Producto auriculares;
	Producto celular;
	Producto tablet;
	Producto zapatillas;
	Producto campera;
	Producto jeans;

	@BeforeEach
	void setUp() throws Exception {
		auriculares= new Producto("Auriculares","auriculares marca samsung",0,4321,"Samsung","Sonido",1000,0.1);
		celular= new Producto("Celular","celular marca samsun",5,7891,"Samsung","electronico",7000,2);
		tablet= new Producto("Tablet","Tablet marca lg",10,0111,"LG","electronico",15000,3.5);
		zapatillas= new Producto("Zapatillas","zapatillas marca converse",20,2134,"Converse","ropa",6000,1);
		campera= new Producto("Campera","campera marca Kevingston",20,2134,"Kevingston","ropa",3000,1.5);
		jeans= new Producto("Jeans","jeans marca Levis",0,1415,"Levis","ropa",3000,0.4);
		
	}

	@Test
	void test() {
		assertEquals(auriculares.precioBase(),auriculares.precioFinal());
		assertEquals(jeans.precioBase(),jeans.precioFinal());
		assertEquals(campera.precioFinal(),campera.precioBase() - (campera.precioBase() * campera.getDescuento()/100));
		assertEquals(celular.precioFinal(),celular.precioBase() - (celular.precioBase() * celular.getDescuento()/100));
		assertEquals(tablet.precioFinal(),tablet.precioBase() - (tablet.precioBase() * tablet.getDescuento()/100));
		assertEquals(zapatillas.precioFinal(),zapatillas.precioBase() - (zapatillas.precioBase() * zapatillas.getDescuento()/100));
	}

}
