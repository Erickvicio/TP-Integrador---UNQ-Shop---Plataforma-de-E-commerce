package main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PedidoTest {

    private Pedido pedido;
    private Estado estadoMock;
    private Item itemMock;

    @BeforeEach
    void setUp() {
        // Al instanciarse acá, se ejecuta el método inicial.
        pedido = new Pedido();
        estadoMock = mock(Estado.class);
        itemMock = mock(Item.class);
    }

    @Test
    void testEstadoInicial_AlCrearUnPedidoDebeAsignarElEstadoBorrador() {
        // Act & Assert
        // Al verificar que arranca siendo una instancia de Borrador, JaCoCo
        // se ve obligado a pintar de verde las líneas de inicialización del Pedido.
        assertNotNull(pedido.getEstado());
        assertInstanceOf(Borrador.class, pedido.getEstado());
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
        // Primero verificamos que arrancó en Borrador (así cubrimos el getter inicial)
        assertInstanceOf(Borrador.class, pedido.getEstado());

        // Cambiamos al mock (así cubrimos el setEstado completo)
        pedido.setEstado(estadoMock);

        // Verificamos el cambio
        assertEquals(estadoMock, pedido.getEstado());
    }

    @Test
    void testMetodosVacios_NoDeberianRomperNiLanzarExcepciones() {
        assertDoesNotThrow(() -> pedido.decrementerStock());
        assertDoesNotThrow(() -> pedido.incrementarStock());
        assertDoesNotThrow(() -> pedido.rembolsaCosto());
        assertDoesNotThrow(() -> pedido.rembolsaEnvio());
        assertDoesNotThrow(() -> pedido.rembolsaCostoYEnvio());
    }
}