package catalogoDeProductos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemDeCatalogoPesoDeCajaTest {
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
        // Inicializamos las cajas (El último parámetro del constructor de Caja asumimos que no afecta al peso inicial)
        caja1 = new Caja("caja de todo", "caja que contiene todo tipo de productos y cajas", 0);
        caja2 = new Caja("caja de alimentos", "caja que contiene alimentos", 0);
        caja3 = new Caja("caja de electronicos", "caja que contiene dispositivos electronicos", 20);
        
        // Inicializamos productos. El último parámetro es el Peso unitario:
        cocacola = new Producto("coca", "bebida coca cola", 10, 1234, "cocacola", "bebida", 50.0, 1.0);
        pizza = new Producto("domino's pizza ", "pizza de local domino", 20, 5678, "domino", "comida", 70.0, 2.0);
        pan = new Producto("pan delicia", "panes de tipo roceta", 0, 9101, "delicia", "comida", 20.0, 1.0);
        galletitas = new Producto("galletitas diversion", "galletitas dulces diversion", 0, 1112, "arcor", "comida", 15.0, 3.0);
        auriculares = new Producto("auriculares samsung", "auriculares de la marca samsung", 0, 1314, "samsung", "sonido", 12.5, 2.0);
        mp3 = new Producto("mp3", "mp3 marca nokia", 0, 1516, "nokia", "sonido", 30.0, 1.0);
        cargador = new Producto("cargador marca appel", "cargar de la marca appel", 2, 1718, "apple", "electronica", 50.0, 1.0);
        kindle = new Producto("kindle marca pepito", "kindle de la marca pepito", 0, 1920, "pepito", "electronica", 60.0, 2.0);
    }

    @Test
    void testCalcularPesoDeLasCajas() {
        // Armamos Caja 3: (2 * auriculares) + (1 * mp3) + (1 * cargador)
        // Pesos: (2 * 2.0) + (1 * 1.0) + (1 * 1.0) = 4.0 + 1.0 + 1.0 = 6.0
        caja3.agregarItem(auriculares, 2);
        caja3.agregarItem(mp3, 1);
        caja3.agregarItem(cargador, 1);
        
        // Armamos Caja 2: (3 * cocacola) + (1 * pizza) + (3 * pan)
        // Pesos: (3 * 1.0) + (1 * 2.0) + (3 * 1.0) = 3.0 + 2.0 + 3.0 = 8.0
        caja2.agregarItem(cocacola, 3);
        caja2.agregarItem(pizza, 1);
        caja2.agregarItem(pan, 3);
        
        // Armamos Caja 1: (3 * galletitas) + (2 * caja2) + (1 * caja3) + (1 * kindle)
        // Pesos: (3 * 3.0) + (2 * 8.0) + (1 * 6.0) + (1 * 2.0)
        // Total: 9.0 + 16.0 + 6.0 + 2.0 = 33.0
        caja1.agregarItem(galletitas, 3);        
        caja1.agregarItem(caja2, 2);
        caja1.agregarItem(caja3, 1);
        caja1.agregarItem(kindle, 1);
        
        // Verificaciones usando assertEquals para doubles (con un pequeño delta de tolerancia de 0.001)
        assertEquals(6.0, caja3.getPeso(), 0.001);
        assertEquals(8.0, caja2.getPeso(), 0.001);
        assertEquals(33.0, caja1.getPeso(), 0.001);
    }
}