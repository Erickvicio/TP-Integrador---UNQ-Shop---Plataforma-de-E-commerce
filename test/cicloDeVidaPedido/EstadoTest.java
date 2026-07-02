package cicloDeVidaPedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoDeProductos.ItemDeCatalogo;
import cicloDeVidaPedido.Cancelado;
import cicloDeVidaPedido.Estado;
import cicloDeVidaPedido.Pedido;
import main.Item;

class EstadoTest {

    private Estado estadoSpy;
    private Pedido pedidoMock;

    @BeforeEach
    void setUp() {
        // 1. Creamos el mock del Pedido
        pedidoMock = mock(Pedido.class);

        // 2. Instanciamos la clase abstracta de forma anónima y le aplicamos un Spy
        estadoSpy = spy(new Estado() {
            // No hace falta sobreescribir nada, hereda el comportamiento base
        });

        // 3. Le asignamos el pedido al atributo público del estado
        estadoSpy.pedido = pedidoMock;
    }

    @Test
    void testCancelado_CambiaElEstadoDelPedidoACancelado() {
        // Act
        estadoSpy.cancelado();

        // Assert: Verificamos que se llame a setEstado en el pedido con una instancia de Cancelado
        verify(pedidoMock).setEstado(any(Cancelado.class));
    }

    @Test
    void testSiguiente_NoHaceNadaPorDefecto() {
        // Act & Assert: Verificamos que corra sin lanzar excepciones (método vacío)
        assertDoesNotThrow(estadoSpy::siguiente);
    }

    @Test
    void testAgregarItem_NoHaceNadaPorDefecto() {
        ItemDeCatalogo itemMock = mock(ItemDeCatalogo.class);
        
        // Act & Assert: Verificamos que corra sin lanzar excepciones (método vacío)
        assertDoesNotThrow(() -> estadoSpy.agregarItem(itemMock));
    }

    @Test
    void testQuitarItem_NoHaceNadaPorDefecto() {
    	ItemDeCatalogo itemMock = mock(ItemDeCatalogo.class);
        
        // Act & Assert: Verificamos que corra sin lanzar excepciones (método vacío)
        assertDoesNotThrow(() -> estadoSpy.quitarItem(itemMock));
    }
}