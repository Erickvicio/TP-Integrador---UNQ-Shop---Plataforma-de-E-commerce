package catalogoDeProductos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoDeProductos.Producto;

class ItemDeCatalogoTestDecremetarStockDeProducto {
	
	Producto auriculares;
	Producto mp3;
	Producto cargador;

	@BeforeEach
	void setUp() throws Exception {
		auriculares= new Producto("auriculares samsung", "auriculares de la marca samsung", 4, 1314, "samsung", "sonido", 12.5, 2);
		mp3= new Producto("mp3", "mp3 marca nokia", 0, 1516, "nokia", "sonido", 30.0,1);
		cargador= new Producto("cargador marca appel", "cargar de la marca appel", 2, 1718, "apple", "electronica", 50.0, 1);
	}

	@Test
	void test() {
		auriculares.incrementarStock(7);
		assertEquals(auriculares.getStock(),7);
		auriculares.decrementarStock(8);
		assertNotEquals(auriculares.getStock(),0);
		
	}
	
	@Test
	void test2() {
		mp3.incrementarStock(10);
		assertEquals(mp3.getStock(),10);
		mp3.decrementarStock(8);
		assertEquals(mp3.getStock(),2);
		
	}
	
	@Test
	void test3() {
		cargador.incrementarStock(5);
		assertEquals(cargador.getStock(),5);
		cargador.decrementarStock(5);
		assertEquals(cargador.getStock(),0);
		
	}

}
