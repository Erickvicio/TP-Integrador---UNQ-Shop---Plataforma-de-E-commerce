package main;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntregadoTest {

    private Entregado entregado;
    private Pedido pedidoMock;
    private Item itemMock;

    @BeforeEach
    void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        entregado = new Entregado(pedidoMock);
    }

    @Test
    void testCancelado_CuandoElPedidoYaSeEntrego_LanzaExcepcion() {

        Exception excepcion = assertThrows(RuntimeException.class, entregado::cancelado);

        assertEquals("Error: Tarde, ya se entrego.", excepcion.getMessage());
    }

    @Test
    void testAgregarItem_CuandoElPedidoYaFueEntregado_LanzaExcepcion() {

    	Exception excepcion = assertThrows(RuntimeException.class, () -> {
    	    entregado.agregarItem(itemMock);
    	});

        assertEquals("Error: El flujo finalizó. El pedido ya fue entregado al cliente.", excepcion.getMessage());
    }

    @Test
    void testQuitarItem_CuandoElPedidoYaFueCerrado_LanzaExcepcion() {

        Exception excepcion = assertThrows(RuntimeException.class, () -> entregado.quitarItem(itemMock));

        assertEquals("Error: El pedido ya fue cerrado y entregado.", excepcion.getMessage());

    }
}