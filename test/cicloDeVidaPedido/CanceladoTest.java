package cicloDeVidaPedido;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cicloDeVidaPedido.Cancelado;
import cicloDeVidaPedido.Pedido;
import main.Item;

import java.util.function.Consumer;

class CanceladoTest {

    private Cancelado cancelado;
    private Pedido pedidoMock;
    private Item itemMock;

    @BeforeEach
    void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        cancelado = new Cancelado(pedidoMock);
    }

    @Test
    void testAgregarItem_CuandoElPedidoEstaCancelado_LanzaExcepcion() {
        // Referenciamos el método con :: puro sin abrir bloques ni flechas
        Consumer<Item> accion = cancelado::agregarItem;

        // JUnit ejecuta la aceptación del consumidor en una sola línea plana
        Exception excepcion = assertThrows(RuntimeException.class, () -> accion.accept(itemMock));

        assertEquals("Error: El pedido está cancelado. Debe iniciar uno nuevo.", excepcion.getMessage());
    }

    @Test
    void testQuitarItem_CuandoElPedidoEstaCancelado_LanzaExcepcion() {
        Consumer<Item> accion = cancelado::quitarItem;

        Exception excepcion = assertThrows(RuntimeException.class, () -> accion.accept(itemMock));

        assertEquals("Error: No se pueden alterar los artículos de un pedido cancelado.", excepcion.getMessage());
    }
}