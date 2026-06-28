package itemCatalogoTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Caja;
import main.Producto;

class ItemDeCatalogoGetNombreyCategoriaTest {
	Caja caja1;
	Caja caja2;

	Producto cocacola;

	@BeforeEach
	void setUp() throws Exception {
		caja1 =new Caja("caja de alimentos","caja que contiene alimentos",0,"comida");//
		caja2 =new Caja("caja de electronicos","caja que contiene dispositivos electronicos",20,"electronica");//
		cocacola= new Producto("coca", "bebida coca cola", 10, 1234, "cocacola", "bebida", 50.0, 1);//
	}

	@Test
	void test() {
		assertEquals(caja1.getNombre(),"caja de alimentos");
		assertEquals(caja1.getCategoria(),"comida");
		assertEquals(caja2.getNombre(),"caja de electronicos");
		assertEquals(caja2.getCategoria(),"electronica");
		assertEquals(cocacola.getNombre(),"coca");
		assertEquals(cocacola.getCategoria(),"bebida");
	}

}
