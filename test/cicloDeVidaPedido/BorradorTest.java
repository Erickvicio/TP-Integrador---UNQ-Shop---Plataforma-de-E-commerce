package cicloDeVidaPedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import catalogoDeProductos.ItemDeCatalogo;
import catalogoDeProductos.Producto;

class BorradorTest {

    Pedido pedidoSpy;
    Borrador borrador;
    ItemDeCatalogo productoPrueba;

    @BeforeEach
    void setUp() throws Exception {
        // Creamos un pedido real
        Pedido pedidoReal = new Pedido(null, null, null);
        
        // Creamos el Spy sobre ese pedido para espiar sus interacciones
        pedidoSpy = spy(pedidoReal);
        
        // Se lo pasamos al borrador
        borrador = new Borrador(pedidoSpy);
        
        // Inicializamos el producto de prueba
        productoPrueba = new Producto("coca", "bebida", 10, 1234, "cocacola", "bebida", 50.0, 1.0);
    }

    @Test
    void testSiguienteCambiaAEstadoConfirmadoYDecrementaStock() {
        // Ejecutamos la transición
        borrador.siguiente();
        
        // 1. Verificamos que se haya llamado al método setEstado en el pedido
        verify(pedidoSpy).setEstado(any(Confirmado.class));
        
        // 2. Verificamos que se haya llamado de forma explícita a decrementar el stock
        // (Ajustá el nombre exacto si es decrementerStock o decrementarStock)
        verify(pedidoSpy).decrementerStock(); 
    }

    @Test
    void testAgregarItemDelegaAlPedido() {
        // 1. Ejecutamos el método del Borrador que querés testear
        borrador.agregarItem(productoPrueba);
        
        // 2. Mockito comprueba el "alcance": verifica si se invocó el método interno de Pedido
        verify(pedidoSpy, times(1)).agregarItem(productoPrueba);
    }

    @Test
    void testQuitarItemDelegaAlPedido() {
        // 1. Ejecutamos el método del Borrador
        borrador.quitarItem(productoPrueba);
        
        // 2. Verificamos que la orden de quitar también haya sido delegada al pedido
        verify(pedidoSpy, times(1)).quitarItem(productoPrueba);
    }
}