import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Pedido;
import main.Estado;
import main.Item;

class PedidoTest {

    private Pedido pedido;
    private Estado estadoMock;
    private Item itemMock;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        estadoMock = mock(Estado.class);
        itemMock = mock(Item.class);
    }

    @Test
    void testAgregarItem_DebeAgregarElProductoALaLista() {
        assertTrue(pedido.getProductos().isEmpty());

        pedido.agregarItem(itemMock);

        assertEquals(1, pedido.getProductos().size());
        assertTrue(pedido.getProductos().contains(itemMock));
    }

    @Test
    void testQuitarItem_DebeEliminarElProductoDeLaLista() {
        pedido.agregarItem(itemMock);
        assertFalse(pedido.getProductos().isEmpty());

        pedido.quitarItem(itemMock);

        assertTrue(pedido.getProductos().isEmpty());
    }

    @Test
    void testSetEstado_DebeCambiarElEstadoDelPedidoYElGetterRetornarlo() {
        assertNull(pedido.getEstado());

        pedido.setEstado(estadoMock);

        assertEquals(estadoMock, pedido.getEstado());
    }

    @Test
    void testMetodosVacios_NoDeberianRomperNiLanzarExcepciones() {
        // Testeamos que los métodos vacíos actuales no tiren errores raros al ejecutarse
        assertDoesNotThrow(() -> pedido.decrementerStock());
        assertDoesNotThrow(() -> pedido.incrementarStock());
        assertDoesNotThrow(() -> pedido.rembolsaCosto());
        assertDoesNotThrow(() -> pedido.rembolsaEnvio());
        assertDoesNotThrow(() -> pedido.rembolsaCostoYEnvio());
    }
}