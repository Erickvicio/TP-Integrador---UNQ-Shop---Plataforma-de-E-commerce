package main;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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

        // Simulamos que la API aprueba los datos
        when(apiTarjetaMock.validarDatos(123456, 123, fechaValida)).thenReturn(true);

        // Al ejecutar, no debería saltar ninguna excepción
        assertDoesNotThrow(() -> tarjetaDeCredito.validarDatos());
        
        // CORREGIDO: Sintaxis correcta de la imagen (método fuera de los paréntesis)
        verify(apiTarjetaMock).validarDatos(123456, 123, fechaValida);
    }

    @Test
    void testValidarDatos_CuandoLaApiRechazaLosDatos_LanzaExcepcion() {
        Date fechaValida = new Date();
        tarjetaDeCredito.setNumero(123456);
        tarjetaDeCredito.setCvv(123);
        tarjetaDeCredito.setVencimiento(fechaValida);

        // Simulamos que la API RECHAZA los datos devolviendo false
        when(apiTarjetaMock.validarDatos(123456, 123, fechaValida)).thenReturn(false);

        // Verificamos que tu método SÍ lance una excepción cuando la API dice que no
        assertThrows(RuntimeException.class, () -> {
            tarjetaDeCredito.validarDatos();
        });
        
        // Verificamos la interacción con la API
        verify(apiTarjetaMock).validarDatos(123456, 123, fechaValida);
    }
    
    @Test
    void testReservarFondos_DebeInvocarPreAutorizarBancoEnLaApi() {
        // Acto: Llamamos al método que queremos testear
        tarjetaDeCredito.reservarFondos();

        // Verificación: Comprobamos que interactuó correctamente con la API externa
        verify(apiTarjetaMock).preAutorizarBanco();
    }

    @Test
    void testEjecutarTransicion_DebeInvocarTransferenciaInmediataEnLaApi() {
        // Acto: Llamamos al método que queremos testear
        tarjetaDeCredito.ejecutarTransicion();

        // Verificación: Comprobamos que interactuó correctamente con la API externa
        verify(apiTarjetaMock).transferenciaInmediata();
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
    void testNotificarResultado_DebeGuardarElCuponEnLaTarjeta() {
        Cupon cuponSimulado = new Cupon();
        cuponSimulado.setNumeroDeCupon(999888);
        cuponSimulado.setPrecioPagado(5000);
        
        when(apiTarjetaMock.generarCupon()).thenReturn(cuponSimulado);

        // Acto
        tarjetaDeCredito.notificarResultado();

        // Verificación en el atributo (estado interno de la tarjeta)
        assertEquals(1, tarjetaDeCredito.getCupones().size(), "La lista debería tener un cupón guardado");
        assertTrue(tarjetaDeCredito.getCupones().contains(cuponSimulado), "El cupón debería estar en el atributo de la tarjeta");
    }
    @Test
    void testGetCupones_DebeDevolverLaListaDeCuponesCorrectamente() {
        // 1. Configuración (Dado que necesitas una instancia real para probar su getter)
        TarjetaDeCredito tarjeta = new TarjetaDeCredito(apiTarjetaMock); // Reemplaza por el nombre real de tu clase hija
        Cupon cuponTest = new Cupon();
        cuponTest.numeroDeCupon = 123456;

        // Añadimos el cupón a la tarjeta (usando tu método de agregar o directamente si el flujo lo hace)
        tarjeta.getCupones().add(cuponTest); 

        // 2. Acto: Llamamos al método que queremos testear
        List<Cupon> resultado = tarjeta.getCupones();

        // 3. Verificación: Validamos que la lista no sea nula, tenga tamaño 1 y contenga nuestro cupón
        assertNotNull(resultado, "La lista devuelta no debería ser null");
        assertEquals(1, resultado.size(), "La lista debería contener exactamente 1 cupón");
        assertEquals(cuponTest, resultado.get(0), "El cupón devuelto debería ser el mismo que se agregó");
    }
/*
    @Test
    void testNotificarResultado_DebeObtenerElCuponDeLaApiYRegistrarloEnLaLista() {
        // Creamos y configuramos el objeto Cupon real basado en tu nueva clase
        Cupon cuponSimulado = new Cupon();
        cuponSimulado.numeroDeCupon = 999888;
        cuponSimulado.precioPagado = 5000; // Asignas un precio de prueba cualquiera
        
        // Simulamos que la API devuelve este objeto Cupon específico
        when(apiTarjetaMock.generarCupon()).thenReturn(cuponSimulado);

        // Acto: Se ejecuta la notificación
        tarjetaDeCredito.notificarResultado();

        // Verificaciones:
        // 1. Chequeamos que interactuó con la API (limpio, estilo de la imagen)
        verify(apiTarjetaMock).generarCupon();
        
        // 2. Validamos que el objeto Cupon se guardó adentro de la lista de la tarjeta
        assertEquals(1, tarjetaDeCredito.getCupones().size(), "La lista debería tener un cupón guardado");
        assertTrue(tarjetaDeCredito.getCupones().contains(cuponSimulado), "El cupón en la lista debería ser el objeto que devolvió la API");
    } */
}