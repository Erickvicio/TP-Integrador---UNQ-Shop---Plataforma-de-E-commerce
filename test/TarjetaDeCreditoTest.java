import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Imports de tu modelo
import main.ApiTarjetaDeCredito;
import main.TarjetaDeCredito;

class TarjetaDeCreditoTest {

    private ApiTarjetaDeCredito apiTarjetaMock;
    private TarjetaDeCredito tarjetaDeCredito;

    @BeforeEach
    void setUp() {
        // Creamos el mock de la interfaz externa
        apiTarjetaMock = mock(ApiTarjetaDeCredito.class);
        // Instanciamos la tarjeta real pasándole el simulador
        tarjetaDeCredito = new TarjetaDeCredito(apiTarjetaMock);
    }

    // ==========================================
    // 1. TESTS DEL MÉTODO: validarDatos()
    // ==========================================

    @Test
    void testValidarDatos_CuandoTodosLosCamposSonCorrectosYLaApiAprueba_NoLanzaExcepcion() {
        Date fechaValida = new Date();
        tarjetaDeCredito.setNumero(123456);
        tarjetaDeCredito.setCvv(123);
        tarjetaDeCredito.setVencimiento(fechaValida);

        // Simulamos que la API aprueba los datos devolviendo true
        when(apiTarjetaMock.validarDatos(123456, 123, fechaValida)).thenReturn(true);

        // Al ejecutar, no debería saltar ninguna excepción
        assertDoesNotThrow(() -> tarjetaDeCredito.validarDatos());
        
        // Verificamos que efectivamente se llamó a la API externa con esos parámetros
        verify(apiTarjetaMock, times(1)).validarDatos(123456, 123, fechaValida);
    }

    @Test
    void testValidarDatos_CuandoElVencimientoEsNull_LanzaExcepcionYNoLlamaALaApi() {
        tarjetaDeCredito.setNumero(123456);
        tarjetaDeCredito.setCvv(123);
        tarjetaDeCredito.setVencimiento(null); // Provocamos el error

        Exception excepcion = assertThrows(RuntimeException.class, () -> {
            tarjetaDeCredito.validarDatos();
        });

        assertEquals("Error: La fecha de vencimiento es nula.", excepcion.getMessage());
        // Clave: Nos aseguramos de que no gaste recursos llamando a la API
        verifyNoInteractions(apiTarjetaMock);
    }

    @Test
    void testValidarDatos_CuandoElCvvEsInvalido_LanzaExcepcionYNoLlamaALaApi() {
        tarjetaDeCredito.setNumero(123456);
        tarjetaDeCredito.setCvv(0); // CVV inválido
        tarjetaDeCredito.setVencimiento(new Date());

        Exception excepcion = assertThrows(RuntimeException.class, () -> {
            tarjetaDeCredito.validarDatos();
        });

        assertEquals("Error: El código CVV es inválido.", excepcion.getMessage());
        verifyNoInteractions(apiTarjetaMock);
    }

    @Test
    void testValidarDatos_CuandoElNumeroEsInvalido_LanzaExcepcionYNoLlamaALaApi() {
        tarjetaDeCredito.setNumero(-5); // Número inválido
        tarjetaDeCredito.setCvv(123);
        tarjetaDeCredito.setVencimiento(new Date());

        Exception excepcion = assertThrows(RuntimeException.class, () -> {
            tarjetaDeCredito.validarDatos();
        });

        assertEquals("Error: El número de tarjeta es inválido.", excepcion.getMessage());
        verifyNoInteractions(apiTarjetaMock);
    }

    @Test
    void testValidarDatos_CuandoLaApiExternaRechazaLaTarjeta_LanzaExcepcionDeRechazo() {
        Date fechaValida = new Date();
        tarjetaDeCredito.setNumero(123456);
        tarjetaDeCredito.setCvv(123);
        tarjetaDeCredito.setVencimiento(fechaValida);

        // Configuramos el Mock para que la API devuelva FALSE (simulando un rechazo del banco)
        when(apiTarjetaMock.validarDatos(123456, 123, fechaValida)).thenReturn(false);

        Exception excepcion = assertThrows(RuntimeException.class, () -> {
            tarjetaDeCredito.validarDatos();
        });

        assertEquals("Error: La API externa rechazó la tarjeta.", excepcion.getMessage());
    }

    // ==========================================
    // 2. TESTS DE LOS OTROS MÉTODOS HEREDADOS
    // ==========================================

    @Test
    void testReservarFondos_DebeLlamarAlMetodoCorrespondienteDeLaApi() {
        tarjetaDeCredito.reservarFondos();
        
        // Verificamos que se delegue la acción a la API externa
        verify(apiTarjetaMock, times(1)).preAutorizarBanco();
    }

    @Test
    void testEjecutarTransicion_DebeLlamarAlMetodoCorrespondienteDeLaApi() {
        tarjetaDeCredito.ejecutarTransicion();
        
        // Verificamos que se delegue la acción a la API externa
        verify(apiTarjetaMock, times(1)).transferenciaInmediata();
    }

   /* @Test
    void testNotificarResultado_DebeObtenerElCuponDeLaApiYRegistrarloEnLaLista() {
        Object cuponSimulado = "CUPON-NRO-999888";
        
        // Simulamos que la API genera y devuelve un cupón específico
        when(apiTarjetaMock.generarCupon()).thenReturn(cuponSimulado);

        // Acto: Se ejecuta la notificación
        tarjetaDeCredito.notificarResultado();

        // Verificaciones:
        // 1. Chequeamos que interactuó con la API para pedir el cupón
        verify(apiTarjetaMock, times(1)).generarCupon();
        
        // 2. ¡La prueba clave que querías! Validamos que el cupón se guardó adentro de la lista de la tarjeta
        assertEquals(1, tarjetaDeCredito.getCupones().size(), "La lista debería tener un cupón guardado");
        assertTrue(tarjetaDeCredito.getCupones().contains(cuponSimulado), "El cupón en la lista debería ser el que devolvió la API");
    }*/
}