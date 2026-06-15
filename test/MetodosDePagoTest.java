import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.MetodosDePago;

class MetodosDePagoTest {

    private MetodosDePago medioPagoSpy;

    @BeforeEach
    void setUp() {
        // Creamos un spy de la clase abstracta. 
        // Esto nos permite controlar el comportamiento de sus métodos abstractos.
        medioPagoSpy = mock(MetodosDePago.class, CALLS_REAL_METHODS);
    }

    @Test
    void testIniciarProcesoPago_CuandoTodosLosPasosSonExitosos_SeEjecutanEnOrden() {
        // Acto: Ejecutamos el método plantilla
        medioPagoSpy.iniciarProcesoPago();

        // Verificación: Comprobamos que se llamaron los 4 métodos obligatorios una sola vez
        verify(medioPagoSpy, times(1)).validarDatos();
        verify(medioPagoSpy, times(1)).reservarFondos();
        verify(medioPagoSpy, times(1)).ejecutarTransicion();
        verify(medioPagoSpy, times(1)).notificarResultado();
    }

    @Test
    void testIniciarProcesoPago_CuandoFallaReservarFondos_SeCortaElFlujoYNoSigue() {
        // Configuración: Forzamos a que el segundo paso (reservarFondos) lance una excepción
        doThrow(new RuntimeException("Error: Fondos insuficientes."))
            .when(medioPagoSpy).reservarFondos();

        // Acto y Verificación: Validamos que salte la excepción al iniciar el pago
        assertThrows(RuntimeException.class, () -> {
            medioPagoSpy.iniciarProcesoPago();
        });

        // Verificación de corte de flujo:
        verify(medioPagoSpy, times(1)).validarDatos();    // El primero sí se ejecutó
        verify(medioPagoSpy, times(1)).reservarFondos(); // El segundo falló acá
        
        // El tercero y cuarto NO debieron ejecutarse nunca porque se cortó el proceso
        verify(medioPagoSpy, never()).ejecutarTransicion();
        verify(medioPagoSpy, never()).notificarResultado();
    }
}