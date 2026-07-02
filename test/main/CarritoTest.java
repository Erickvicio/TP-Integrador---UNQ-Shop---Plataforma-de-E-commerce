package main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import catalogoDeProductos.ItemDeCatalogo;
import main.Carrito;
import main.DetallePedido;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarritoTest {

    private Carrito carrito;
    private ItemDeCatalogo itemMock1;
    private ItemDeCatalogo itemMock2;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        itemMock1 = mock(ItemDeCatalogo.class);
        itemMock2 = mock(ItemDeCatalogo.class);
    }

    // --- TESTS DE AGREGAR ITEM ---

    @Test
    void testAgregarItem_CantidadPorDefecto_CreaNuevoDetalle() {
        // Al agregar 1 por defecto y no existir, invoca verificarStockSuficiente en el item
        carrito.agregarItem(itemMock1);

        ArrayList<DetallePedido> detalles = carrito.getDetallePedidos();
        assertEquals(1, detalles.size());
        assertEquals(itemMock1, detalles.get(0).getItem());
        assertEquals(1, detalles.get(0).getCantidad());
        verify(itemMock1, times(1)).verificarStockSuficiente(1);
    }

    @Test
    void testAgregarItem_VariasVeces_ItemNuevo_CreaNuevoDetalle() {
        carrito.agregarItem_veces(itemMock1, 5);

        ArrayList<DetallePedido> detalles = carrito.getDetallePedidos();
        assertEquals(1, detalles.size());
        assertEquals(5, detalles.get(0).getCantidad());
        verify(itemMock1, times(1)).verificarStockSuficiente(5);
    }

    @Test
    void testAgregarItem_ItemYaExistente_IncrementaCantidadYValidaStock() {
        // Primera carga (creación)
        carrito.agregarItem_veces(itemMock1, 2);
        
        // Segunda carga (actualización): Tiene 2, sumamos 3. Total a validar = 5
        carrito.agregarItem_veces(itemMock1, 3);

        ArrayList<DetallePedido> detalles = carrito.getDetallePedidos();
        assertEquals(1, detalles.size());
        assertEquals(5, detalles.get(0).getCantidad());
    }

    @Test
    void testAgregarItem_CantidadInvalida_LanzaRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            carrito.agregarItem_veces(itemMock1, 0);
        });
        assertTrue(exception.getMessage().contains("La cantidad a agregar debe ser mayor a 0"));
        
        assertThrows(RuntimeException.class, () -> carrito.agregarItem_veces(itemMock1, -5));
    }

    // --- TESTS DE QUITAR ITEM ---

    @Test
    void testQuitarItem_CantidadParcial_DecrementaUnidades() {
        // Cargamos 5 unidades inicialmente
        carrito.agregarItem_veces(itemMock1, 5);
        
        // Simulamos el comportamiento del item para la pertenencia en la búsqueda
        // Dado que buscarDetallePorItem usa detalle.perteneceAlItem(item)
        // Como el DetallePedido real usa .equals(unItem), el mock de item responderá bien
        carrito.quitarItem_veces(itemMock1, 2);

        ArrayList<DetallePedido> detalles = carrito.getDetallePedidos();
        assertEquals(1, detalles.size());
        assertEquals(3, detalles.get(0).getCantidad());
    }

    @Test
    void testQuitarItem_CantidadTotal_RemueveElDetalleCompleto() {
        carrito.agregarItem_veces(itemMock1, 3);
        
        // Quitamos las 3 unidades exactas que tiene
        carrito.quitarItem(itemMock1); // por defecto quita 1
        carrito.quitarItem_veces(itemMock1, 2); // quitamos las 2 restantes

        ArrayList<DetallePedido> detalles = carrito.getDetallePedidos();
        assertTrue(detalles.isEmpty());
    }

    @Test
    void testQuitarItem_InexistenteEnElCarrito_LanzaRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            carrito.quitarItem(itemMock1);
        });
        assertEquals("El producto no existe en el pedido.", exception.getMessage());
    }

    @Test
    void testQuitarItem_CantidadInvalida_LanzaRuntimeException() {
        carrito.agregarItem(itemMock1);
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            carrito.quitarItem_veces(itemMock1, -1);
        });
        assertTrue(exception.getMessage().contains("La cantidad a agregar debe ser mayor a 0"));
    }

    // --- TESTS DE DELEGACIÓN MASIVA DE STOCK ---

    @Test
    void testDecrementarStock_DisparaAccionEnTodosLosDetalles() {
        // Para testear esto aisladamente y asegurar el foreach, usamos mocks en la lista directamente
        DetallePedido detalleMock1 = mock(DetallePedido.class);
        DetallePedido detalleMock2 = mock(DetallePedido.class);
        
        carrito.getDetallePedidos().add(detalleMock1);
        carrito.getDetallePedidos().add(detalleMock2);

        carrito.decrementarStock();

        verify(detalleMock1, times(1)).decrementarStock();
        verify(detalleMock2, times(1)).decrementarStock();
    }

    @Test
    void testIncrementarStock_DisparaAccionEnTodosLosDetalles() {
        DetallePedido detalleMock1 = mock(DetallePedido.class);
        DetallePedido detalleMock2 = mock(DetallePedido.class);
        
        carrito.getDetallePedidos().add(detalleMock1);
        carrito.getDetallePedidos().add(detalleMock2);

        carrito.incrementarStock();

        verify(detalleMock1, times(1)).incrementarStock();
        verify(detalleMock2, times(1)).incrementarStock();
    }

    // --- TESTS DE BÚSQUEDA Y CÁLCULOS (STREAMS) ---

    @Test
    void testBuscarDetallePorItem_NoEncontrado_RetornaNull() {
        assertNull(carrito.buscarDetallePorItem(itemMock1));
    }

    @Test
    void testGetPrecioTotal_SumaDeTodosLosPrecios() {
        DetallePedido detalleMock1 = mock(DetallePedido.class);
        DetallePedido detalleMock2 = mock(DetallePedido.class);
        
        when(detalleMock1.getPrecio()).thenReturn(150.0);
        when(detalleMock2.getPrecio()).thenReturn(350.5);
        
        carrito.getDetallePedidos().add(detalleMock1);
        carrito.getDetallePedidos().add(detalleMock2);

        // 150.0 + 350.5 = 500.5f
        assertEquals(500.5f, carrito.getPrecioTotal());
    }

    @Test
    void testGetPesoTotal_SumaDeTodosLosPesos() {
        DetallePedido detalleMock1 = mock(DetallePedido.class);
        DetallePedido detalleMock2 = mock(DetallePedido.class);
        
        when(detalleMock1.getPeso()).thenReturn(12.0);
        when(detalleMock2.getPeso()).thenReturn(5.5);
        
        carrito.getDetallePedidos().add(detalleMock1);
        carrito.getDetallePedidos().add(detalleMock2);

        // 12.0 + 5.5 = 17.5f
        assertEquals(17.5f, carrito.getPesoTotal());
    }
}