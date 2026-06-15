import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Imports de tu modelo
import main.ApiTransferenciaBancaria;
import main.TransferenciaBancaria;

class TransferenciaBancariaTest {

    private ApiTransferenciaBancaria apiTransferenciaMock;
    private TransferenciaBancaria transferenciaBancaria;

    @BeforeEach
    void setUp() {
        // 1. Creamos el mock de la API de transferencias
        apiTransferenciaMock = mock(ApiTransferenciaBancaria.class);
        
        // 2. Instanciamos la clase a probar
        transferenciaBancaria = new TransferenciaBancaria(apiTransferenciaMock);
        
        // Reflect para asegurar que la lista interna no sea null si no tenés constructor armado
        // (En caso de que ya la inicialices en tu constructor real, esto no molesta)
        // transferenciaBancaria.setComprobantesTransferencia(new ArrayList<>());
    }

    // ==========================================
    // 1. TESTS DEL MÉTODO: validarDatos()
    // ==========================================

    @Test
    void testValidarDatos_CuandoTodosLosCamposSonCorrectosYLaApiAprueba_NoLanzaExcepcion() {
        transferenciaBancaria.setcvu(123456);
        transferenciaBancaria.setAlias("mi.alias.unq");

        // Simulamos que la API aprueba la combinación de CVU y Alias
        when(apiTransferenciaMock.validarDatos(123456, "mi.alias.unq")).thenReturn(true);

        // Verificamos que no explote nada
        assertDoesNotThrow(() -> transferenciaBancaria.validarDatos());
        
        // Comprobamos la delegación con los parámetros correspondientes
        verify(apiTransferenciaMock, times(1)).validarDatos(123456, "mi.alias.unq");
    }

    @Test
    void testValidarDatos_CuandoElCvuEsCero_LanzaExcepcionYNoLlamaALaApi() {
        transferenciaBancaria.setcvu(0); // Provocamos error
        transferenciaBancaria.setAlias("mi.alias.unq");

        Exception excepcion = assertThrows(RuntimeException.class, () -> {
            transferenciaBancaria.validarDatos();
        });

        assertEquals("Error: El cvu/Cbu es nulo.", excepcion.getMessage());
        verifyNoInteractions(apiTransferenciaMock);
    }

    @Test
    void testValidarDatos_CuandoElAliasEstaVacio_LanzaExcepcionYNoLlamaALaApi() {
        transferenciaBancaria.setcvu(123456);
        transferenciaBancaria.setAlias(""); // Provocamos error (String vacío)

        Exception excepcion = assertThrows(RuntimeException.class, () -> {
            transferenciaBancaria.validarDatos();
        });

        assertEquals("Error: El alias es inválido.", excepcion.getMessage());
        verifyNoInteractions(apiTransferenciaMock);
    }

    @Test
    void testValidarDatos_CuandoLaApiRechazaLaTransferencia_LanzaExcepcionDeRechazo() {
        transferenciaBancaria.setcvu(123456);
        transferenciaBancaria.setAlias("alias.invalido");

        // Configuramos el Mock para que devuelva false simulando un rechazo de COELSA o del banco
        when(apiTransferenciaMock.validarDatos(123456, "alias.invalido")).thenReturn(false);

        Exception excepcion = assertThrows(RuntimeException.class, () -> {
            transferenciaBancaria.validarDatos();
        });

        assertEquals("Error: La API externa rechazó la transfencia Bancaria.", excepcion.getMessage());
    }

    // ==========================================
    // 2. TESTS DE LOS OTROS MÉTODOS HEREDADOS
    // ==========================================

    @Test
    void testReservarFondos_NoHaceNadaYNoSeComunicaConLaApi() {
        // Como tu método está vacío (las transferencias suelen validar saldo directo al ejecutar),
        // testeamos que correrlo no rompa el flujo ni interactúe con el mock.
        assertDoesNotThrow(() -> transferenciaBancaria.reservarFondos());
        verifyNoInteractions(apiTransferenciaMock);
    }

    @Test
    void testEjecutarTransicion_DebeLlamarAlMetodoDeTransferenciaDeLaApi() {
        transferenciaBancaria.ejecutarTransicion();
        
        // Verificamos que se llame al método largo de la interfaz de transferencias
        verify(apiTransferenciaMock, times(1)).transferenciaInmediataOProgramada();
    }

    @Test
    void testNotificarResultado_DebeObtenerElComprobanteYRegistrarloEnLaLista() {
        // Creamos un objeto genérico que simule ser el comprobante emitido
    	main.ComprobanteTransferenciaBancaria comprobanteSimulado = mock(main.ComprobanteTransferenciaBancaria.class);
        
        // Cuando se le pida el cupón/comprobante a la API, devuelve nuestro simulado
        when(apiTransferenciaMock.generarCupon()).thenReturn(comprobanteSimulado);

        // Acto: Se ejecuta la notificación de la transferencia
        transferenciaBancaria.notificarResultado();

        // Verificaciones
        verify(apiTransferenciaMock, times(1)).generarCupon();
        
        // Comprobamos que el elemento se guardó correctamente en la lista interna de la clase
        assertNotNull(transferenciaBancaria.getComprobantesTransferencia(), "La lista no debería ser null");
        assertEquals(1, transferenciaBancaria.getComprobantesTransferencia().size(), "Debería haber un comprobante registrado");
        assertTrue(transferenciaBancaria.getComprobantesTransferencia().contains(comprobanteSimulado), "El elemento guardado debe ser el comprobante devuelto por la API");
    }
}