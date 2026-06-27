package itemCatalogoTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Atributo;
import main.DatoInvalido;
import main.Producto;

class ItemDeCatalogoTestDatosInvalidos {


	@BeforeEach
	void setUp() throws Exception {
	
	}

	@Test
	void test() {
		assertThrows(DatoInvalido.class,()->{new Atributo("",3);});
		
		assertThrows(DatoInvalido.class,()->{new Atributo("Calorias",null);});
		
		assertThrows(DatoInvalido.class,()->{new Producto("Arbejas", "", 10, 1746, "Marolio", "alimento",500, 5);});
		
		assertThrows(DatoInvalido.class,()->{new Producto("", "lentejas marca marolio", 0, 1745, "Marolio", "alimento",450,5);});
		
		assertThrows(DatoInvalido.class,()->{new Producto("Hamburguesas", "hamburguesas marca swift", -12, 1747, "swift", "alimento",450,5);});
		
		assertThrows(DatoInvalido.class,()->{new Producto("Hamburguesas", "hamburguesas de macdonalds", 0, -1749, "macdonalds", "alimento",600,6);});
		
		assertThrows(DatoInvalido.class,()->{new Producto("Pan", "Pan de la marca delicia", 0, 1750, "", "alimento",450,5);});
		
		assertThrows(DatoInvalido.class,()->{new Producto("Pan", "Pan de la marca MamaCocina", 0, 1751, "MamaCocina", "",120,2);});
		
		assertThrows(DatoInvalido.class,()->{new Producto("Caramelos", "Caramelos marca Dulce", 0, 1752, "Dulce", "golosina",-120.0,2);});
		
		assertThrows(DatoInvalido.class,()->{new Producto("Aceite", "Aceite de marca Oliva", 0, 1753, "Oliva", "ingrediemte",100.0,-1);});
		
		
		
	}

}
