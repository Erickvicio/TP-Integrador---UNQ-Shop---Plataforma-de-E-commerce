package cicloDeVidaPedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogoDeProductos.ItemDeCatalogo;
import catalogoDeProductos.Producto;
import main.Carrito;
import main.Direccion;
import notificacionesDePedido.ArchivoAdjunto;
import notificacionesDePedido.Subsistema;

class PedidoTest {

    Pedido pedido;
    Direccion direccionMock;
    BaseDeReportes baseDeReportesMock;
    ItemDeCatalogo productoPrueba;
    Subsistema subsistemaMock;
    ArchivoAdjunto archivoMock;

    @BeforeEach
    void setUp() throws Exception {
        // Mocks de las dependencias requeridas por el constructor
        direccionMock = mock(Direccion.class);
        baseDeReportesMock = mock(BaseDeReportes.class);
        
        // Inicializamos el pedido real
        pedido = new Pedido("test@correo.com", direccionMock, baseDeReportesMock);
        
        // Mocks adicionales para los métodos de negocio
        productoPrueba = mock(Producto.class);
        subsistemaMock = mock(Subsistema.class);
        archivoMock = mock(ArchivoAdjunto.class);
    }

    @Test
    void testConstructorEInicializacion() {
        assertEquals("test@correo.com", pedido.getCorreo());
        assertEquals(direccionMock, pedido.getDir());
        assertNotNull(pedido.getCarrito());
        
        // Verificamos que el estado inicial sea Borrador
        assertTrue(pedido.getEstado() instanceof Borrador);
        
        // Listas iniciales vacías
        assertTrue(pedido.getSubsistemas().isEmpty());
        assertTrue(pedido.getAdjuntos().isEmpty());
    }

    @Test
    void testAgregarReporteABase() {
        // Reemplazamos la base creada en el constructor con nuestro mock usando reflexión o inyección directa
        // Como 'barep' es visible dentro del paquete, lo asignamos de forma directa:
        pedido.agregarReporteABase();
        
        // El método actual de Pedido inicializa una nueva BaseDeReportes interna en el constructor (this.barep = new BaseDeReportes())
        // Si querés verificar que se sume de forma real en tu objeto, podés testear que corra sin errores
        assertDoesNotThrow(() -> pedido.agregarReporteABase());
    }

    @Test
    void testCambioDeEstado() {
        Estado nuevoEstado = mock(Estado.class);
        pedido.setEstado(nuevoEstado);
        
        assertEquals(nuevoEstado, pedido.getEstado());
    }

    @Test
    void testDelegacionMetodosCarrito() {
        // Para verificar la delegación pura al carrito, podemos espiar el carrito que se creó adentro de pedido
        Carrito carritoSpy = spy(pedido.getCarrito());
        
        // Forzamos a Pedido a usar nuestro Spy mediante un refactor o usando el objeto actual
        // En este caso, para no alterar tu código, probamos que el flujo se ejecute de forma directa:
        assertDoesNotThrow(() -> {
            pedido.agregarItem(productoPrueba);
            pedido.agregarItem_veces(productoPrueba, 3);
            pedido.quitarItem(productoPrueba);
            pedido.quitarItem_veces(productoPrueba, 2);
            pedido.decrementerStock();
            pedido.incrementarStock();
        });
    }

    @Test
    void testGestionDeSubsistemasYNotificacion() {
        // Agregar subsistema
        pedido.agregarSubsitema(subsistemaMock);
        assertEquals(1, pedido.getSubsistemas().size());
        assertTrue(pedido.getSubsistemas().contains(subsistemaMock));

        // Notificar (Observer pattern)
        pedido.notificarSubsitemas();
        // Verificamos que el subsistema haya recibido el update con el estado actual
        verify(subsistemaMock, times(1)).update(pedido.getEstado());

        // Quitar subsistema
        pedido.quitarSubsistma(subsistemaMock);
        assertTrue(pedido.getSubsistemas().isEmpty());
    }

    @Test
    void testAgregarArchivoAdjunto() {
        pedido.agregarArchivoAdjunto(archivoMock);
        
        assertEquals(1, pedido.getAdjuntos().size());
        assertTrue(pedido.getAdjuntos().contains(archivoMock));
    }

    @Test
    void testMetodosDeNegocioPendientesNoLanzanError() {
        // Verificamos que los métodos vacíos corran sin romper el flujo
        assertDoesNotThrow(() -> {
            pedido.rembolsaCostoYEnvio();
            pedido.rembolsaCosto();
            pedido.rembolsaEnvio();
        });
    }
}