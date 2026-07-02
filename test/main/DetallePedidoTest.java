package main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import catalogoDeProductos.ItemDeCatalogo;
import main.DetallePedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DetallePedidoTest {

    private DetallePedido detallePedido;
    private ItemDeCatalogo itemMock;
    private final int CANTIDAD_INICIAL = 5;

    @BeforeEach
    void setUp() {
        // Creamos el mock de la clase abstracta
        itemMock = mock(ItemDeCatalogo.class);
        // Inicializamos el sujeto bajo prueba (SUT)
        detallePedido = new DetallePedido(CANTIDAD_INICIAL, itemMock);
    }

    // --- TESTS DE VALIDACIÓN Y CONTROL ---

    @Test
    void testVerificarStockParaCantidad_ConStockSuficiente_DelegaCorrectamente() {
        int cantidadAValidar = 10;
        
        // Ejecutamos el método
        detallePedido.verificarStockParaCantidad(cantidadAValidar);
        
        // Verificamos que efectivamente le delegó la responsabilidad al item mockeado
        verify(itemMock, times(1)).verificarStockSuficiente(cantidadAValidar);
    }

    @Test
    void testVerificarStockParaCantidad_ConItemNulo_LanzaRuntimeException() {
        detallePedido.setItem(null);
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            detallePedido.verificarStockParaCantidad(10);
        });
        
        assertEquals("El ítem de catálogo no puede ser nulo.", exception.getMessage());
    }

    @Test
    void testVerificarDisponibilidadParaQuitar_CantidadValida_NoLanzaExcepcion() {
        // Quitar 3 unidades teniendo 5 es válido
        assertDoesNotThrow(() -> detallePedido.verificarDisponibilidadParaQuitar(3));
    }

    @Test
    void testVerificarDisponibilidadParaQuitar_CantidadExcesiva_LanzaRuntimeException() {
        int cantidadAQuitar = 10; // 10 > 5
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            detallePedido.verificarDisponibilidadParaQuitar(cantidadAQuitar);
        });
        
        assertTrue(exception.getMessage().contains("No podés quitar más unidades de las que tiene el pedido."));
    }

    @Test
    void testSuperaCantidadActual_CasoVerdaderoYFalso() {
        assertTrue(detallePedido.superaCantidadActual(6), "6 debería superar la cantidad actual de 5");
        assertFalse(detallePedido.superaCantidadActual(5), "5 NO debería superar la cantidad actual de 5");
        assertFalse(detallePedido.superaCantidadActual(3), "3 NO debería superar la cantidad actual de 5");
    }

    @Test
    void testPerteneceAlItem_MismoItem_RetornaTrue() {
        assertTrue(detallePedido.perteneceAlItem(itemMock));
    }

    @Test
    void testPerteneceAlItem_OtroItemUItemNulo_RetornaFalse() {
        ItemDeCatalogo otroItemMock = mock(ItemDeCatalogo.class);
        
        assertFalse(detallePedido.perteneceAlItem(otroItemMock));
        assertFalse(detallePedido.perteneceAlItem(null));
        
        // Caso borde: si el item del detalle es nulo
        detallePedido.setItem(null);
        assertFalse(detallePedido.perteneceAlItem(itemMock));
    }

    // --- TESTS DE STOCK (DELEGACIÓN) ---

    @Test
    void testDecrementarStock_DelegaAlItemConSuCantidad() {
        detallePedido.decrementarStock();
        verify(itemMock, times(1)).decrementarStock(CANTIDAD_INICIAL);
    }

    @Test
    void testIncrementarStock_DelegaAlItemConSuCantidad() {
        detallePedido.incrementarStock();
        verify(itemMock, times(1)).incrementarStock(CANTIDAD_INICIAL);
    }

    // --- TESTS DE MODIFICACIÓN DE CANTIDAD ---

    @Test
    void testIncrementarCantidad() {
        detallePedido.incrementarCantidad(3);
        assertEquals(8, detallePedido.getCantidad());
    }

    @Test
    void testDecrementarCantidad() {
        detallePedido.decrementarCantidad(2);
        assertEquals(3, detallePedido.getCantidad());
    }

    // --- TESTS DE CÁLCULOS Y GETTERS/SETTERS ---

    @Test
    void testGetPrecio_CalculaConPrecioFinalDelItem() {
        // Simulamos que el método abstracto precioFinal() del mock devuelve 100.0
        when(itemMock.precioFinal()).thenReturn(100.0);
        
        // 100.0 * 5 unidades = 500.0
        assertEquals(500.0, detallePedido.getPrecio());
        verify(itemMock, times(1)).precioFinal();
    }

    @Test
    void testGetPeso_CalculaConPesoDelItem() {
        // Simulamos que el método abstracto getPeso() del mock devuelve 2.5
        when(itemMock.getPeso()).thenReturn(2.5);
        
        // 2.5 * 5 unidades = 12.5
        assertEquals(12.5, detallePedido.getPeso());
        verify(itemMock, times(1)).getPeso();
    }

    @Test
    void testGettersYSetters() {
        ItemDeCatalogo nuevoItem = mock(ItemDeCatalogo.class);
        detallePedido.setCantidad(10);
        detallePedido.setItem(nuevoItem);
        
        assertEquals(10, detallePedido.getCantidad());
        assertEquals(nuevoItem, detallePedido.getItem());
    }
}