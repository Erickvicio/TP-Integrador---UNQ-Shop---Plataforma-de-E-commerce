package itemCatalogoTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Caja;
import main.Producto;

class ItemDeCatalogoTestDecremetarStockDeCajaSingularTest {
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
		//Aca testeo suponiendo que un pedido se cancelo y se tiene que reponer el stock de los productos que se guardan dentro de una caja
		
		caja3.agregarItem(auriculares, 3);
		caja3.agregarItem(cargador, 3);
		caja3.incrementarStock(5);
		//tenemos 15 en el stock de auriculares  
		//tenemos 15 en el stock de cargadores
		caja3.decrementarStock();
		assertEquals(auriculares.getStock(),12);
		assertEquals(cargador.getStock(), 12);
	}
	
	@Test
	void test2() {
		//Aca testeo suponiendo que un pedido se cancelo y se tiene que reponer el stock de los productos dentro de una caja
		
		caja2.agregarItem(cocacola, 2);
		caja2.agregarItem(pizza, 1);
		caja2.agregarItem(pan, 3);
		caja2.incrementarStock(4);
		//tenemos 8 en el stock de cocacola
		//tenemos 4 en el stock de pizza
		//tenemos 12 en el stock de  pan
		caja2.decrementarStock();;
		
		assertEquals(cocacola.getStock(),6);
		assertEquals(pizza.getStock(),3);
		assertEquals(pan.getStock(), 9);
		
	}
	
	@Test
	void test3() {
		//Aca testeo suponiendo que un pedido se cancelo y se tiene que reponer el stock de los productos o cajas dentro de otra caja
		//tambien que com aumenta si un producto dentro de la caja , aparece en otra caja
		galletitas.incrementarStock(6);
		caja3.agregarItem(auriculares, 3);
		caja3.agregarItem(cargador, 3);
		caja3.incrementarStock(5);
		//tenemos 15 en el stock de auriculares  
		//tenemos 15 en el stock de cargadores
		
		caja2.agregarItem(cocacola, 2);
		caja2.agregarItem(pizza, 1);
		caja2.agregarItem(pan, 3);
		caja2.incrementarStock(4);
		//tenemos 8 en el stock de cocacola
		//tenemos 4 en el stock de pizza
		//tenemos 12 en el stock de  pan
		
		caja1.agregarItem(caja3, 2);
		//tenemos 15 en el stock de auriculares  
		//tenemos 15 en el stock de cargadores
		
		caja1.agregarItem(caja2, 3);
		//tenemos 8 en el stock de cocacola
		//tenemos 4 en el stock de pizza
		//tenemos 12 en el stock de  pan
		
		caja1.agregarItem(galletitas, 2);
		caja1.agregarItem(auriculares, 1);
		caja1.decrementarStock();
		
		assertEquals(auriculares.getStock(),8);
		assertEquals(cargador.getStock(), 9);
		assertEquals(cocacola.getStock(),2);
		assertEquals(pizza.getStock(),1);
		assertEquals(pan.getStock(), 3);
		assertEquals(galletitas.getStock(), 4);
	}
}
