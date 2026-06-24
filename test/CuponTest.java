

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Cupon;

class CuponTest {

    @Test
    void testGettersYSetters_DeberianDefinirYDevolverLosValoresCorrectamente() {
        // Instancia vacía
        Cupon cupon = new Cupon();

        // Probamos Setters
        cupon.setNumeroDeCupon(111222);
        cupon.setPrecioPagado(4500);

        // Probamos Getters
        assertEquals(111222, cupon.getNumeroDeCupon());
        assertEquals(4500, cupon.getPrecioPagado());
    }

    @Test
    void testConstructorConParametros_DeberiaInicializarLosAtributos() {
        // Probamos el constructor completo
        Cupon cupon = new Cupon(999888, 7500);

        assertEquals(999888, cupon.getNumeroDeCupon());
        assertEquals(7500, cupon.getPrecioPagado());
    }
}