package metodosDeEnvio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import catalogoDeProductos.Caja;
import catalogoDeProductos.Producto;
import cicloDeVidaPedido.Pedido;
import main.Direccion;
import metodosDeEnvio.CorreoArgentino;
import metodosDeEnvio.EnvioExpress;
import metodosDeEnvio.Estandar;
import metodosDeEnvio.Express;
import metodosDeEnvio.RetiroEnSucursal;
import metodosDeEnvio.ServicioEnvio;
import metodosDeEnvio.Sucursal;
import metodosDeEnvio.TipoDeEnvio;

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
	void setUp(){
		
		//Set up
		mCorreoArgentino 	= Mockito.mock(CorreoArgentino.class);
		mEnvioExpress 		= Mockito.mock(EnvioExpress.class);
		mSucursal 			= Mockito.mock(Sucursal.class);
//		unPedido 			= Mockito.mock(Pedido.class);
		
		//Test Double Instalation
		sEstandar 		= new Estandar(mCorreoArgentino);	
		sExpress 		= new Express(mEnvioExpress);
		sRetiroSucursal = new RetiroEnSucursal(mSucursal);
		
		
		dir = Mockito.mock(Direccion.class);
		unPedido = new Pedido("familiaaquinoerick@gmail.com", dir);
		
		Producto unProducto =
				new Producto(
			            "Polystation",
			            "Consola de sobremesa familiar",
			            0,
			            1000,
			            "GLK",
			            "Electronica",
			            40999,
			            2.2
			    );
		unProducto.incrementarStock(15);
		
				
		Caja unaCaja = new Caja(
	            "Caja Oferta Navidad",
	            "Caja navideña con productos varios",
	            20,
	            "Hogar"
	    );
		unaCaja.incrementarStock(15);
		
		unPedido.agregarItem(unaCaja);  
		unPedido.agregarItem(unProducto);
		
		sE = new ServicioEnvio(unPedido);
	}
	
	
	@Test
	void costeEstimadoEnvioEstandar() {
		
		//Test Double Configuration
//		when(sEstandar.calcularCostos(unPedido)).thenReturn(51100f);
		when(mCorreoArgentino.estimarEnvio(unPedido.getPeso(), unPedido.getDir())).thenReturn(51100f);
		
		//Exercise
		sE.establecerTipo(sEstandar);
		
		float obtenido = sE.costoEstimado();
		float esperado = 51100f;			
		
		//Verify
		verify(mCorreoArgentino, times(1)).estimarEnvio(unPedido.getPeso(), unPedido.getDir());	//Verifica si se interactuo 1 vez con el mock
		assertEquals(esperado, obtenido);
	}
	

	@Test
	void diasEstimadosEntregaEnvioEstandar() {
		
		//Test Double Configuration
//		when(sEstandar.entregaEstimada(unPedido)).thenReturn(7);
		
		//Exercise
		sE.establecerTipo(sEstandar);
		
		int obtenido = sE.estimacionEntregaEnDias();
		int esperado = 7;
		
		//Verify
//		verify(sEstandar, times(1)).entregaEstimada(unPedido);
		assertEquals(esperado, obtenido);
	}
	
	@Test
	void costeEstimadoEnvioExpress() {
		
		//Test Double Configuration
//		when(sExpress.calcularCostos(unPedido)).thenReturn(76300f);
		when(mEnvioExpress.calcularCosto(unPedido.getPrecio())).thenReturn(76300f);
		
		//Exercise
		sE.establecerTipo(sExpress);
		
		float obtenido = sE.costoEstimado();
		float esperado = 76300f;			
		
		//Verify
		verify(mEnvioExpress, times(1)).calcularCosto(unPedido.getPrecio());	//Verifica si se interactuo 1 vez con el mock
		assertEquals(esperado, obtenido);
	}
	

	@Test
	void diasEstimadosEntregaEnvioExpress() {
		
		//Test Double Configuration
//		when(sExpress.entregaEstimada(unPedido)).thenReturn(1);		
		
		//Exercise
		sE.establecerTipo(sExpress);
		
		int obtenido = sE.estimacionEntregaEnDias();
		int esperado = 1;
		
		//Verify
//		verify(sExpress, times(1)).entregaEstimada(unPedido);
		assertEquals(esperado, obtenido);
	}

	@Test
	void costeEstimadoRetiroSucursal() {
		
		//Test Double Configuration
//		when(sRetiroSucursal.calcularCostos(unPedido)).thenReturn(0f);
		
		//Exercise
		sE.establecerTipo(sRetiroSucursal);
		
		float obtenido = sE.costoEstimado();
		float esperado = 0f;			
		
		//Verify
//		verify(sRetiroSucursal, times(1)).calcularCostos(unPedido);	//Verifica si se interactuo 1 vez con el mock
		assertEquals(esperado, obtenido);
	}
	

	@Test
	void diasEstimadosEntregaConStockRetiroSucursal() {
		
		//Test Double Configuration
		when(mSucursal.hayStock(unPedido)).thenReturn(true);		
		
		//Exercise
		sE.establecerTipo(sRetiroSucursal);
		
		int obtenido = sE.estimacionEntregaEnDias();
		int esperado = 1;
		
		//Verify
		verify(mSucursal, times(1)).hayStock(unPedido);
		assertEquals(esperado, obtenido);
	}
	
	@Test
	void diasEstimadosEntregaSinStockRetiroSucursal() {
		
		//Test Double Configuration
		when(mSucursal.hayStock(unPedido)).thenReturn(false);		
		
		//Exercise
		sE.establecerTipo(sRetiroSucursal);
		
		int obtenido = sE.estimacionEntregaEnDias();
		int esperado = 3;
		
		//Verify
		verify(mSucursal, times(1)).hayStock(unPedido);
		assertEquals(esperado, obtenido);
	}

}
