package cicloDeVidaPedido;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoDeProductos.ItemDeCatalogo;
import cicloDeVidaPedido.Cancelado;
import cicloDeVidaPedido.Confirmado;
import cicloDeVidaPedido.En_Preparacion;
import cicloDeVidaPedido.Pedido;
import main.Item;

class ConfirmadoTest {

    private Confirmado confirmado;
    private Pedido pedidoMock;
    private ItemDeCatalogo itemMock;

    @BeforeEach
    void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(ItemDeCatalogo.class);
        confirmado = new Confirmado(pedidoMock);
    }

    @Test
    void testCancelado_CambiaEstadoYAumentaStock() {
        // Act
        confirmado.cancelado();

        // Assert: Verifica que pase a Cancelado e incremente stock
        verify(pedidoMock).setEstado(any(Cancelado.class));
        verify(pedidoMock).incrementarStock();
    }

    @Test
    void testSiguiente_CambiaElEstadoAEnPreparacion() {
        // Act
        confirmado.siguiente();

        // Assert: Verifica el cambio al siguiente estado
        verify(pedidoMock).setEstado(any(En_Preparacion.class));
    }

    @Test
    void testAgregarItem_CuandoElPedidoEstaConfirmado_LanzaExcepcion() {
        // Act & Assert: Usamos el :: con nuestro método auxiliar
        Exception excepcion = assertThrows(RuntimeException.class, this::ejecutarAgregarItem);

        assertEquals("Error: El pedido ya está confirmado. No se pueden sumar más artículos.", excepcion.getMessage());
    }

    @Test
    void testQuitarItem_CuandoElPedidoEstaConfirmado_LanzaExcepcion() {
        // Act & Assert: Usamos el :: con nuestro método auxiliar
        Exception excepcion = assertThrows(RuntimeException.class, this::ejecutarQuitarItem);

        assertEquals("Error: El pedido ya está confirmado. Modificación denegada.", excepcion.getMessage());
    }

    // ==========================================
    // MÉTODOS AUXILIARES PARA LOGRAR EL VERDE CON ::
    // ==========================================
    private void ejecutarAgregarItem() {
        confirmado.agregarItem(itemMock);
    }

    private void ejecutarQuitarItem() {
        confirmado.quitarItem(itemMock);
    }
}