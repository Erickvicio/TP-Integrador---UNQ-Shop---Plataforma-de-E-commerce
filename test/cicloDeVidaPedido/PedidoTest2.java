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
import metodoDePago.ApiTarjetaDeCredito;
import metodoDePago.TarjetaDeCredito;
import metodosDeEnvio.CorreoArgentino;
import metodosDeEnvio.Estandar;
import notificacionesDePedido.ArchivoAdjunto;
import notificacionesDePedido.Subsistema;

class PedidoTest3 {

    Pedido pedido;
    Direccion direccionMock;
    BaseDeReportes baseDeReportesMock;
    ItemDeCatalogo productoPrueba;
    Subsistema subsistemaMock;
    ArchivoAdjunto archivoMock;
    ApiTarjetaDeCredito apiTarjeta;
    TarjetaDeCredito tarjetaBBVA;
    CorreoArgentino apiEnvioEstandar; 
    Estandar tipoEnvio;

    @BeforeEach
    void setUp() throws Exception {
        // Mocks de las dependencias requeridas por el constructor
        direccionMock = mock(Direccion.class);
        baseDeReportesMock = mock(BaseDeReportes.class);
        apiTarjeta = mock(ApiTarjetaDeCredito.class);
		tarjetaBBVA = new TarjetaDeCredito(apiTarjeta);
		apiEnvioEstandar = mock(CorreoArgentino.class);
		tipoEnvio = new Estandar(apiEnvioEstandar);
    
        // Inicializamos el pedido real
        pedido = new Pedido("test@correo.com", direccionMock, baseDeReportesMock,tarjetaBBVA,tipoEnvio);
        
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
        // 1. Creamos la base de reportes real y le inicializamos la lista para que no tire NullPointerException
        BaseDeReportes baseSana = new BaseDeReportes();
        baseSana.ventas = new ArrayList<Venta>(); // Inicializamos la lista interna
        
        // 2. Usamos Reflection para inyectar esta base sana en el atributo privado 'barep' de nuestro objeto pedido
        assertDoesNotThrow(() -> {
            java.lang.reflect.Field field = Pedido.class.getDeclaredField("barep");
            field.setAccessible(true); // Rompemos el "private" temporalmente para el test
            field.set(pedido, baseSana); // Le asignamos la base sana al objeto 'pedido' del setUp
        });

        // 3. Ahora ejecutamos el método de negocio con el entorno correctamente preparado
        assertDoesNotThrow(() -> {
            pedido.agregarReporteABase();
        });
        
        // 4. Verificamos que efectivamente se haya guardado la venta en la lista de la base
        assertEquals(1, baseSana.ventas.size(), "La base de reportes debería haber registrado la venta.");
    }
    @Test
    void testCambioDeEstado() {
        Estado nuevoEstado = mock(Estado.class);
        pedido.setEstado(nuevoEstado);
        
        assertEquals(nuevoEstado, pedido.getEstado());
    }

    @Test
    void testDelegacionMetodosCarrito() {
        // Probamos que los flujos delegados se ejecuten sin lanzar errores de estructura
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

}