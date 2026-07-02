package cicloDeVidaPedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogoDeProductos.ItemDeCatalogo;
import catalogoDeProductos.Producto;

class BorradorTest {

    Pedido pedidoMock; // Cambiamos spy por un Mock puro
    Borrador borrador;
    ItemDeCatalogo productoPrueba;

    @BeforeEach
    void setUp() throws Exception {
        // Creamos un Mock completo de Pedido. No ejecuta código real, aislándolo de Carrito y BaseDeReportes.
        pedidoMock = mock(Pedido.class);
        
        // Se lo pasamos al borrador
        borrador = new Borrador(pedidoMock);
        
        // Inicializamos el producto de prueba
        productoPrueba = new Producto("coca", "bebida", 10, 1234, "cocacola", "bebida", 50.0, 1.0);
    }

    @Test
    void testSiguienteCambiaAEstadoConfirmadoYDecrementaStock() {
        // Ejecutamos la transición
        borrador.siguiente();
        
        // 1. Verificamos que se haya llamado al método setEstado en el pedido con cualquier Confirmado
        verify(pedidoMock).setEstado(any(Confirmado.class));
        
        // 2. Verificamos que se haya llamado a decrementar el stock
        verify(pedidoMock).decrementerStock(); 
    }

    @Test
    void testAgregarItemDelegaAlPedido() {
        // 1. Ejecutamos el método del Borrador
        borrador.agregarItem(productoPrueba);
        
        // 2. Verificamos que se haya invocado exactamente el método en el pedido sin ejecutar su interior
        verify(pedidoMock, times(1)).agregarItem(productoPrueba);
    }

    @Test
    void testQuitarItemDelegaAlPedido() {
        // 1. Ejecutamos el método del Borrador
        borrador.quitarItem(productoPrueba);
        
        // 2. Verificamos que la orden de quitar haya sido delegada al pedido
        verify(pedidoMock, times(1)).quitarItem(productoPrueba);
    }
}