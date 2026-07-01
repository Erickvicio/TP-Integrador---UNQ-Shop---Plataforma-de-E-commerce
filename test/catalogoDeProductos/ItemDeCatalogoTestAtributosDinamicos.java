package catalogoDeProductos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoDeProductos.Atributo;
import catalogoDeProductos.Producto;

class ItemDeCatalogoTestAtributosDinamicos extends ItemDeCatalogoPrecioDeCajaTest {

	Atributo calorias;
	Atributo azucar;
	Atributo grasas;
	Atributo sal;

	Producto galletitas;
	Producto papasFritas;
	
	
	@BeforeEach
	void setUp() throws Exception {
		calorias = new Atributo("calorias",7);
		azucar= new Atributo("azucar",10);
		grasas= new Atributo("grasas",13);
		sal= new Atributo("sal",13);
		
		galletitas=new Producto("galletitas diversion", "galletitas dulces diversion", 0, 1112, "arcor", "comida", 15.0, 3);
		papasFritas=new Producto("Papasfritas Lays", "papas fritas de marca lays", 0, 2221, "lays", "comida", 15.6, 2);
		
		
	}

	@Test
	void test() {
		galletitas.agregarAtributo("calorias", 7);
		galletitas.agregarAtributo("azucar", 10);
		galletitas.agregarAtributo("azucar", 10);
		assertEquals(galletitas.getAtributosExtra().size(),3);
	}
	
	@Test
	void test2() {
		papasFritas.agregarAtributo("calorias", 7);
		papasFritas.agregarAtributo("azucar", 10);
		papasFritas.agregarAtributo("azucar", 10);
		assertEquals(papasFritas.getAtributosExtra().size(),3);
		assertThrows(DatoInvalido.class,()->{papasFritas.agregarAtributo("",null);});
	}

}
