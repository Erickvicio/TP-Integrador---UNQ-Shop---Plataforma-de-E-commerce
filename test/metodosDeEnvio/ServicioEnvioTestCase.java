package metodosDeEnvio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cicloDeVidaPedido.Pedido;
import main.Direccion;

class ServicioEnvioTestCase {
    
    private ServicioEnvio sE;
    
    private TipoDeEnvio sEstandar;
    private TipoDeEnvio sExpress;
    private TipoDeEnvio sRetiroSucursal;
    
    private Pedido unPedido;
    private CorreoArgentino mCorreoArgentino;
    private EnvioExpress mEnvioExpress;
    private Direccion dir;
    private Sucursal mSucursal;
    
    @BeforeEach
    void setUp() {
        // 1. Instanciamos los mocks de las dependencias externas
        mCorreoArgentino = Mockito.mock(CorreoArgentino.class);
        mEnvioExpress    = Mockito.mock(EnvioExpress.class);
        mSucursal        = Mockito.mock(Sucursal.class);
        dir              = Mockito.mock(Direccion.class);
        
        // 2. Mockeamos el pedido por completo para aislar el test del modelo real
        unPedido = Mockito.mock(Pedido.class);
        
        // 3. Inicializamos las estrategias concretas pasándoles sus colaboradores
        sEstandar       = new Estandar(mCorreoArgentino);    
        sExpress        = new Express(mEnvioExpress);
        sRetiroSucursal = new RetiroEnSucursal(mSucursal);
        
        // 4. Inicializamos el objeto bajo prueba (SUT)
        sE = new ServicioEnvio(unPedido);
    }
    
    @Test
    void costeEstimadoEnvioEstandar() {
        // Configuración del Mock: Cuando el servicio pida datos al pedido, respondemos con stubs fijos
        when(unPedido.getPeso()).thenReturn(2.5f);
        when(unPedido.getDir()).thenReturn(dir);
        when(mCorreoArgentino.estimarEnvio(2.5f, dir)).thenReturn(51100f);
        
        // Ejercicio
        sE.establecerTipo(sEstandar);
        float obtenido = sE.costoEstimado();
        float esperado = 51100f;            
        
        // Verificación
        verify(mCorreoArgentino, times(1)).estimarEnvio(2.5f, dir);    
        assertEquals(esperado, obtenido, 0.001);
    }
    
    @Test
    void diasEstimadosEntregaEnvioEstandar() {
        // Ejercicio
        sE.establecerTipo(sEstandar);
        int obtenido = sE.estimacionEntregaEnDias();
        int esperado = 7; // Asumiendo que Estandar devuelve 7 fijo internamente
        
        // Verificación
        assertEquals(esperado, obtenido);
    }
    
    @Test
    void costeEstimadoEnvioExpress() {
        // Configuración del Mock
        when(unPedido.getPrecio()).thenReturn(40000f);
        when(mEnvioExpress.calcularCosto(40000f)).thenReturn(76300f);
        
        // Ejercicio
        sE.establecerTipo(sExpress);
        float obtenido = sE.costoEstimado();
        float esperado = 76300f;            
        
        // Verificación
        verify(mEnvioExpress, times(1)).calcularCosto(40000f);    
        assertEquals(esperado, obtenido, 0.001);
    }
    
    @Test
    void diasEstimadosEntregaEnvioExpress() {
        // Ejercicio
        sE.establecerTipo(sExpress);
        int obtenido = sE.estimacionEntregaEnDias();
        int esperado = 1; // Asumiendo que Express devuelve 1 fijo internamente
        
        // Verificación
        assertEquals(esperado, obtenido);
    }

    @Test
    void costeEstimadoRetiroSucursal() {
        // Ejercicio
        sE.establecerTipo(sRetiroSucursal);
        float obtenido = sE.costoEstimado();
        float esperado = 0f;            
        
        // Verificación
        assertEquals(esperado, obtenido, 0.001);
    }
    
    @Test
    void diasEstimadosEntregaConStockRetiroSucursal() {
        // Configuración del Mock
        when(mSucursal.hayStock(unPedido)).thenReturn(true);        
        
        // Ejercicio
        sE.establecerTipo(sRetiroSucursal);
        int obtenido = sE.estimacionEntregaEnDias();
        int esperado = 1;
        
        // Verificación
        verify(mSucursal, times(1)).hayStock(unPedido);
        assertEquals(esperado, obtenido);
    }
    
    @Test
    void diasEstimadosEntregaSinStockRetiroSucursal() {
        // Configuración del Mock
        when(mSucursal.hayStock(unPedido)).thenReturn(false);        
        
        // Ejercicio
        sE.establecerTipo(sRetiroSucursal);
        int obtenido = sE.estimacionEntregaEnDias();
        int esperado = 3;
        
        // Verificación
        verify(mSucursal, times(1)).hayStock(unPedido);
        assertEquals(esperado, obtenido);
    }
}