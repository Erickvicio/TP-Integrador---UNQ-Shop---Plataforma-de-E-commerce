package main;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorradorTest {

    private Borrador borrador;
    private Pedido pedidoMock;
    private Item itemMock;

    @BeforeEach
    void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(Item.class);
        borrador = new Borrador(pedidoMock);
    }

    @Test
    void testSiguiente_CambiaElEstadoAConfirmadoYDecrementaStock() {
        // Act
        borrador.siguiente();

        // Assert: Verifica que pase a Confirmado y reste del stock
        verify(pedidoMock).setEstado(any(Confirmado.class));
        verify(pedidoMock).decrementerStock();
    }

    @Test
    void testAgregarItem_DelegaLaAccionAlPedido() {
        // Act & Assert: Usamos el :: con nuestro método auxiliar
        assertDoesNotThrow(this::ejecutarAgregarItem);

        // Verifica que el estado borrador le mande el item al pedido real
        verify(pedidoMock).agregarItem(itemMock);
    }

    @Test
    void testQuitarItem_DelegaLaAccionAlPedido() {
        // Act & Assert: Usamos el :: con nuestro método auxiliar
        assertDoesNotThrow(this::ejecutarQuitarItem);

        // Verifica que el estado borrador remueva el item del pedido real
        verify(pedidoMock).quitarItem(itemMock);
    }

    // ==========================================
    // MÉTODOS AUXILIARES PARA LOGRAR EL VERDE CON ::
    // ==========================================
    private void ejecutarAgregarItem() {
        borrador.agregarItem(itemMock);
    }

    private void ejecutarQuitarItem() {
        borrador.quitarItem(itemMock);
    }
}