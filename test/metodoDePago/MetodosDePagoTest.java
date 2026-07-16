package metodoDePago;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import metodoDePago.MetodosDePago;


class MetodosDePagoTest {

    private MetodosDePago medioPagoSpy;

    @BeforeEach
    void setUp() {
        // Correcto: Usamos spy para que corra el método plantilla real
        medioPagoSpy = spy(MetodosDePago.class); 
    }

    @Test
    void testIniciarProcesoPago_CuandoTodosLosPasosSonExitosos_SeEjecutanEnOrden() {
        // Acto: Ejecutamos el método de la clase
        medioPagoSpy.iniciarProcesoPago(475889);

        // Verificaciones limpias según la imagen
        verify(medioPagoSpy).validarDatos();
        verify(medioPagoSpy).reservarFondos();
        verify(medioPagoSpy).ejecutarTransicion(475889); 
        verify(medioPagoSpy).notificarResultado();
    }

    @Test
    void testIniciarProcesoPago_CuandoFallaReservarFondos_SeCortaElFlujoYNoSigue() {
        // 1. Arrange: Forzamos a que el spy explote al reservar fondos
        doThrow(new RuntimeException("Error: Fondos insuficientes."))
            .when(medioPagoSpy).reservarFondos();

        // 2. Act & Assert: Usamos referencia de método sin lambdas para asegurar el verde absoluto
        assertThrows(Exception.class, 
        		() -> medioPagoSpy.iniciarProcesoPago(485541));
 
        // 3. Verify: Verificaciones de corte de flujo limpias
        verify(medioPagoSpy).validarDatos();    
        verify(medioPagoSpy).reservarFondos(); 
        
        verify(medioPagoSpy, never()).ejecutarTransicion(485541);
        verify(medioPagoSpy, never()).notificarResultado();
    }
    
}