package main;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class ComprobanteTransferenciaBancariaTest {

    @Test
    void testGettersYSetters_DeberiaDefinirYDevolverElNumeroDeOperacion() {
        // Instancia vacía
        ComprobanteTransferenciaBancaria comprobante = new ComprobanteTransferenciaBancaria();

        // Probamos Setter
        comprobante.setNumeroOperacion(555444333);

        // Probamos Getter
        assertEquals(555444333, comprobante.getNumeroOperacion());
    }

    @Test
    void testConstructorConParametros_DeberiaInicializarElNumeroDeOperacion() {
        // Probamos el constructor
        ComprobanteTransferenciaBancaria comprobante = new ComprobanteTransferenciaBancaria(12345678);

        assertEquals(12345678, comprobante.getNumeroOperacion());
    }
}