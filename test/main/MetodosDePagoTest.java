package main;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        medioPagoSpy.iniciarProcesoPago();

        // Verificaciones limpias según la imagen
        verify(medioPagoSpy).validarDatos();
        verify(medioPagoSpy).reservarFondos();
        verify(medioPagoSpy).ejecutarTransicion(); 
        verify(medioPagoSpy).notificarResultado();
    }

    @Test
    void testIniciarProcesoPago_CuandoFallaReservarFondos_SeCortaElFlujoYNoSigue() {
        // Solución al error de la imagen: Para métodos void SÍ o SÍ se usa doThrow.
        doThrow(new RuntimeException("Error: Fondos insuficientes."))
            .when(medioPagoSpy).reservarFondos();

        // Acto y Verificación: Validamos que salte la excepción al iniciar el pago
     // Cambiar RuntimeException.class por Exception.class (o la excepción específica que lance)
        assertThrows(Exception.class, () -> {
            medioPagoSpy.iniciarProcesoPago();
            
        });

        // Verificación de corte de flujo (estilo limpio de la imagen)
        verify(medioPagoSpy).validarDatos();    
        verify(medioPagoSpy).reservarFondos(); 
        
        // Uso correcto de never() según los modos de verificación de la imagen
        verify(medioPagoSpy, never()).ejecutarTransicion();
        verify(medioPagoSpy, never()).notificarResultado();
    }
    
}