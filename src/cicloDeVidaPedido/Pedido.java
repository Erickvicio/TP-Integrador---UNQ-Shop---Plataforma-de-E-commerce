package cicloDeVidaPedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cicloDeVidaPedido.BaseDeReportes;
import catalogoDeProductos.ItemDeCatalogo;
import main.Carrito;
import main.Direccion;
import metodoDePago.MetodosDePago;
import metodosDeEnvio.TipoDeEnvio;
import notificacionesDePedido.ArchivoAdjunto;
import notificacionesDePedido.Subsistema;

public class Pedido { 

    private String correo;
	private ArrayList<Subsistema> subsistemas;
    private ArrayList<ArchivoAdjunto> adjuntos; 
    private Estado estado; 
    private Direccion dir;
    private Carrito carrito; 		
    private BaseDeReportes barep; 	
    

    
    
    //2)
    //Conexion con metodos de pago *********************
    private MetodosDePago metodoPago;
    
    public void setMetodoDePago(MetodosDePago metodoPago) {
    	this.metodoPago = metodoPago;
    }
    
    private void realizarPago() {
    	
    	this.estado.iniciarProcesoDePago(getPrecioConEnvio());
    	this.estado.siguiente();
    	this.notificarSubsitemas();
    	
//    	metodoPago.iniciarProcesoPago(getPrecioConEnvio());
    }
    
    public MetodosDePago getMetodoDePago() {
    	return this.metodoPago;
    }
    //**************************************************
    
    //4) 
    private TipoDeEnvio tipoEnvio;
    
    public float costeEnvioEstimado() {
    	return tipoEnvio.calcularCostos(this);
    }
    
    public int estimacionEntregaDelEnvio() {
    	return tipoEnvio.entregaEstimada(this);
    }
    
    public void setTipoDeEnvio(TipoDeEnvio tipoEnvio) {
    	this.tipoEnvio = tipoEnvio;
    }
    //*******************************************************
    
    
    
    //3)
    //SUGERENCIA **********************************************************
//    private HashMap<ItemDeCatalogo, Integer> items;
    // Podria ser mejor que el sistema de carritos y detalles de pedido
    // FIN DE SUGERENCIA **************************************************

    
    /*
     * Metodo para saber que contiene un pedido
     * */
  
    // Constructor 
    public Pedido(String correo,Direccion dir,BaseDeReportes barep) {
		this.correo=correo;
		this.estadoInicial();
		this.subsistemas= new ArrayList<>();
		this.adjuntos= new ArrayList<>();
		this.dir = dir;
        this.carrito = new Carrito();
        this.barep = new BaseDeReportes();
        
	}

    // New costructor que incluye el metodo de pago
    public Pedido(String correo,Direccion dir,BaseDeReportes barep, MetodosDePago metodoPago) {
    	this.correo=correo;
		this.estadoInicial();
		this.subsistemas= new ArrayList<>();
		this.adjuntos= new ArrayList<>();
		this.dir = dir;
        this.carrito = new Carrito();
        this.barep = new BaseDeReportes();
    	this.metodoPago = metodoPago;
	}
	
    // New costructor que incluye el metodo de pago
    public Pedido(String correo,Direccion dir,BaseDeReportes barep, MetodosDePago metodoPago,TipoDeEnvio tipoEnvio) {
    	this.correo=correo;
		this.estadoInicial();
		this.subsistemas= new ArrayList<>();
		this.adjuntos= new ArrayList<>();
		this.dir = dir;
        this.carrito = new Carrito();
        this.barep = new BaseDeReportes();
    	this.metodoPago = metodoPago;
    	this.tipoEnvio = tipoEnvio;
	}
	

    private void estadoInicial() {
    	this.setEstado(new Borrador(this)); 
    }

//    // VOIDS
    public void agregarReporteABase() {
    	this.barep.agregarReporte(this.getCarrito(), LocalDate.now());
    }
    
    
    private Object getbarep() {
		// TODO Auto-generated method stub
		return this.barep;
	} 


	public void agregarItem(ItemDeCatalogo item) { 
        this.carrito.agregarItem(item); 
    }
    
    public void agregarItem_veces(ItemDeCatalogo item, int vecesAgregar) {
        this.carrito.agregarItem_veces(item, vecesAgregar);
    } 

    public void quitarItem(ItemDeCatalogo item) { 
        this.carrito.quitarItem(item); 
    } 
    
    public void quitarItem_veces(ItemDeCatalogo item, int cantidadAQuitar) { 
        this.carrito.quitarItem_veces(item, cantidadAQuitar);
    } 

    public void decrementerStock() { 
        this.carrito.decrementarStock();
    } 

    public void incrementarStock() { 
        this.carrito.incrementarStock(); 
    }
    
    // Métodos de negocio pendientes
    public void rembolsaCostoYEnvio(){
    	metodoPago.reembolsar(getPrecioConEnvio());
    } 
    public void rembolsaCosto() {
    	metodoPago.reembolsar(getPrecio());
    } 
    public void rembolsaEnvio() {
    	metodoPago.reembolsar(costeEnvioEstimado());
    } 

    // --- GETTERS Y SETTERS DE PEDIDO ---

    public Carrito getCarrito() {
        return this.carrito;
    }

    public Estado getEstado() { 
        return this.estado; 
    } 
     
    public Direccion getDir() { 
        return this.dir; 
    } 
     
    // Delegan el cálculo de streams directamente a lo que responda el carrito
    public float getPrecio() {
        return this.carrito.getPrecioTotal(); 
    } 
     
    public float getPrecioConEnvio() {
    	return this.getPrecio() + this.costeEnvioEstimado();
    }
    
    public float getPeso() { 
        return this.carrito.getPesoTotal(); 
    } 

    public void setEstado(Estado estado) { 
        this.estado = estado; 
    }
     
     public void agregarSubsitema(Subsistema susbistema) {
		this.subsistemas.add(susbistema);
	}

     public void notificarSubsitemas() {
		this.subsistemas.stream().forEach(s -> s.update(estado));
	}
	
	public void quitarSubsistma(Subsistema subsistema) {
		this.subsistemas.remove(subsistema);
		
	}
	
	public void agregarArchivoAdjunto(ArchivoAdjunto archivo) {
		this.adjuntos.add(archivo);
	}
	
	public ArrayList<Subsistema> getSubsistemas() {
		return subsistemas;
	}

	public String getCorreo() {
		return correo;
	}

     public ArrayList<ArchivoAdjunto> getAdjuntos() {
		return adjuntos;
	}

	 public void confirmarPedido() {
		 this.realizarPago();
//		 this.estado.siguiente();
	 }
}
