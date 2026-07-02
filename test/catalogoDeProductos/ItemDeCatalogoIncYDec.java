package catalogoDeProductos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemDeCatalogoStockMetodosTest {
    Producto cocacola;

    @BeforeEach
    void setUp() throws Exception {
        // Inicializamos un producto concreto para poder probar los métodos heredados.
        // El stock inicial arranca en 0 según el constructor de ItemDeCatalogo.
        cocacola = new Producto("coca", "bebida coca cola", 10, 1234, "cocacola", "bebida", 50.0, 1.0);
    }

    @Test
    void testIncrementarStock() {
        // Ejecutamos el incremento
        cocacola.incrementarStock(15);
        
        // Verificamos que se haya sumado correctamente
        assertEquals(15, cocacola.getStock());
    }

    @Test
    void testDecrementarStock() {
        // Primero incrementamos para tener un colchón de stock
        cocacola.incrementarStock(20);
        
        // Ejecutamos el decremento
        cocacola.decrementarStock(7);
        
        // Verificamos que se haya restado de forma exacta (20 - 7 = 13)
        assertEquals(13, cocacola.getStock());
    }
}