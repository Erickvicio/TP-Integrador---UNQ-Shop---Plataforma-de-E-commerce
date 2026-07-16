package catalogoDeProductos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoDeProductos.Caja;
import catalogoDeProductos.Producto;

class ItemDeCatalogoTestDecremetarStockDeCaja  {
	Caja caja1;
	Caja caja2;
	Caja caja3;

	Producto cocacola;
	Producto pizza;
	Producto pan;
	Producto galletitas;
	Producto auriculares;
	Producto mp3;
	Producto cargador;
	Producto kindle;

	@BeforeEach
	void setUp() throws Exception {
	
		caja1 =new Caja("caja de todo","caja que contiene todo tipo de productos y cajas",10);// 
		caja2 =new Caja("caja de alimentos","caja que contiene alimentos",0);//
		caja3 =new Caja("caja de electronicos","caja que contiene dispositivos electronicos",20);//
		cocacola= new Producto("coca", "bebida coca cola", 10, 1234, "cocacola", "bebida", 50.0, 1);//
		pizza= new Producto("domino's pizza ", "pizza de local domino", 20, 5678, "domino", "comida", 70.0, 2);//
		pan= new Producto("pan delicia", "panes de tipo roceta", 0, 9101, "delicia", "comida", 20.0, 1);
		galletitas= new Producto("galletitas diversion", "galletitas dulces diversion", 0, 1112, "arcor", "comida", 15.0, 3);
		auriculares= new Producto("auriculares samsung", "auriculares de la marca samsung", 4, 1314, "samsung", "sonido", 12.5, 2);
		mp3= new Producto("mp3", "mp3 marca nokia", 0, 1516, "nokia", "sonido", 30.0,1);
		cargador= new Producto("cargador marca appel", "cargar de la marca appel", 2, 1718, "apple", "electronica", 50.0, 1);
	}

	@Test
	void test1() {
		
		caja3.agregarItem(auriculares, 3);
		caja3.agregarItem(cargador, 3);
		caja3.incrementarStock(3);
		
		
		caja3.decrementarStock(2);
		assertEquals(caja3.getStock(),1);
	}
	

	@Test
	void test2() {
		
		galletitas.incrementarStock(6);
		caja3.agregarItem(auriculares, 3);
		caja3.agregarItem(cargador, 3);
		caja3.incrementarStock(5);
		
		caja2.agregarItem(cocacola, 2);
		caja2.agregarItem(pizza, 1);
		caja2.agregarItem(pan, 3);
		caja2.incrementarStock(4);
		
		assertEquals(caja3.getStock(), 5);
		assertEquals(caja2.getStock(), 4);
		
	}

	
}


