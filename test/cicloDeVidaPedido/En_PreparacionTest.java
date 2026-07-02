package cicloDeVidaPedido;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoDeProductos.ItemDeCatalogo;
import cicloDeVidaPedido.Cancelado;
import cicloDeVidaPedido.En_Preparacion;
import cicloDeVidaPedido.Enviado;
import cicloDeVidaPedido.Pedido;
import main.Item;

class En_PreparacionTest {

    private En_Preparacion enPreparacion;
    private Pedido pedidoMock;
    private ItemDeCatalogo itemMock;

    @BeforeEach
    void setUp() {
        pedidoMock = mock(Pedido.class);
        itemMock = mock(ItemDeCatalogo.class);
        enPreparacion = new En_Preparacion(pedidoMock);
    }

    @Test
    void testCancelado_CambiaEstadoIncrementaStockYReembolsaCostoEnvio() {
        // Act: Al no tener parámetros, usamos la referencia directa del objeto
        assertDoesNotThrow(enPreparacion::cancelado);

        // Assert: Verificamos todo el flujo interno del método
        verify(pedidoMock).setEstado(any(Cancelado.class));
        verify(pedidoMock).incrementarStock();
        verify(pedidoMock).rembolsaCostoYEnvio();
    }

    @Test
    void testSiguiente_CambiaElEstadoAEnviado() {
        // Act: Al no tener parámetros, usamos la referencia directa del objeto
        assertDoesNotThrow(enPreparacion::siguiente);

        // Assert: Verifica la transición al estado Enviado
        verify(pedidoMock).setEstado(any(Enviado.class));
    }

    @Test
    void testAgregarItem_CuandoEstaEnPreparacion_LanzaExcepcion() {
        // Act & Assert: Usamos el :: con nuestro método auxiliar privado
        Exception excepcion = assertThrows(RuntimeException.class, this::ejecutarAgregarItem);

        assertEquals("Error: El pedido ya está en preparación en el depósito.", excepcion.getMessage());
    }

    @Test
    void testQuitarItem_CuandoEstaEnPreparacion_LanzaExcepcion() {
        // Act & Assert: Usamos el :: con nuestro método auxiliar privado
        Exception excepcion = assertThrows(RuntimeException.class, this::ejecutarQuitarItem);

        assertEquals("Error: No se pueden remover ítems de un pedido en preparación.", excepcion.getMessage());
    }

    // ==========================================
    // MÉTODOS AUXILIARES PARA LOGRAR EL VERDE CON ::
    // ==========================================
    private void ejecutarAgregarItem() {
        enPreparacion.agregarItem(itemMock);
    }

    private void ejecutarQuitarItem() {
        enPreparacion.quitarItem(itemMock);
    }
}