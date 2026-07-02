package cicloDeVidaPedido;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoDeProductos.ItemDeCatalogo;
import cicloDeVidaPedido.Cancelado;
import cicloDeVidaPedido.Entregado;
import cicloDeVidaPedido.Enviado;
import cicloDeVidaPedido.Pedido;
import main.Item;

class EnviadoTest {

    private Enviado enviado;
    private Pedido pedidoMock;
    private ItemDeCatalogo itemMock;

    @BeforeEach
    void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(ItemDeCatalogo.class);
        enviado = new Enviado(pedidoMock);
    }

    @Test
    void testCancelado_CambiaEstadoAumentaStockYReembolsa() {
        enviado.cancelado();

        verify(pedidoMock).setEstado(any(Cancelado.class));
        verify(pedidoMock).incrementarStock();
        verify(pedidoMock).rembolsaCosto();
    }

    @Test
    void testSiguiente_CambiaElEstadoAEntregado() {
        enviado.siguiente();

        verify(pedidoMock).setEstado(any(Entregado.class));
    }

    @Test
    void testAgregarItem_CuandoElPaqueteEstaEnCamino_LanzaExcepcion() {
        // Act & Assert: Usamos el :: llamando a nuestro método auxiliar de abajo
        Exception excepcion = assertThrows(RuntimeException.class, this::ejecutarAgregarItem);

        assertEquals("Error: El paquete ya fue despachado y está en camino.", excepcion.getMessage());
    }

    @Test
    void testQuitarItem_CuandoElPaqueteEstaEnCamino_LanzaExcepcion() {
        // Act & Assert: Usamos el :: llamando a nuestro método auxiliar de abajo
        Exception excepcion = assertThrows(RuntimeException.class, this::ejecutarQuitarItem);

        assertEquals("Error: Imposible quitar artículos. El transporte ya tiene el paquete.", excepcion.getMessage());
    }

    // ==========================================
    // MÉTODOS AUXILIARES PARA LOGRAR EL VERDE CON ::
    // ==========================================
    private void ejecutarAgregarItem() {
        enviado.agregarItem(itemMock);
    }

    private void ejecutarQuitarItem() {
        enviado.quitarItem(itemMock);
    }
}