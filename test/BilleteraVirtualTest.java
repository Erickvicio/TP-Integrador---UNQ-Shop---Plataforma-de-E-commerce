import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.ApiBilleteraVirtual;
import main.BilleteraVirtual;

class BilleteraVirtualTest {

    private ApiBilleteraVirtual apiBilleteraMock;
    private BilleteraVirtual billeteraVirtual;

    @BeforeEach
    void setUp() {
        apiBilleteraMock = mock(ApiBilleteraVirtual.class);
        billeteraVirtual = new BilleteraVirtual(apiBilleteraMock);
    }

    @Test
    void testValidarDatos_CuandoTieneSaldoSuficiente_NoLanzaExcepcion() {
        when(apiBilleteraMock.verificarSaldoSuficiente()).thenReturn(true);

        assertDoesNotThrow(() -> billeteraVirtual.validarDatos());

        verify(apiBilleteraMock, times(1)).verificarSaldoSuficiente();
    }

    @Test
    void testValidarDatos_CuandoNoTieneSaldoSuficiente_LanzaExcepcion() {
        when(apiBilleteraMock.verificarSaldoSuficiente()).thenReturn(false);

        Exception excepcion = assertThrows(RuntimeException.class, () -> {
            billeteraVirtual.validarDatos();
        });

        assertEquals("Error: Saldo insuficiente en la cuenta.", excepcion.getMessage());
        verify(apiBilleteraMock, times(1)).verificarSaldoSuficiente();
    }

    @Test
    void testReservarFondos_DebeBloquearElSaldoEnLaApi() {
        billeteraVirtual.reservarFondos();

        verify(apiBilleteraMock, times(1)).bloquearSaldoHastaConfirmar();
    }

    @Test
    void testEjecutarTransicion_DebeAcreditarAlVendedorEnLaApi() {
        billeteraVirtual.ejecutarTransicion();

        verify(apiBilleteraMock, times(1)).acreditarEnTiempoRealAlVendedor();
    }

    @Test
    void testNotificarResultado_DebeEnviarLaNotificacionPushEnLaApi() {
        billeteraVirtual.notificarResultado();

        verify(apiBilleteraMock, times(1)).enviarNotificacionPush();
    }
}