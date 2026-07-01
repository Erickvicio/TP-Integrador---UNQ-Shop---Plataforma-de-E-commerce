package main; 

public class Pedido { 

    private String correo;
	private ArrayList<Subsistema> subsistemas;
     private ArrayList<ArchivoAdjunto> adjuntos; 
    private Carrito carrito; 
    private Estado estado; 
    private Direccion dir; 

    // Constructor 
    public Pedido(String correo,Direccion dir) {
		this.correo=correo;
		this.estadoInicial();
		this.subsistemas= new ArrayList<>();
		this.adjuntos= new ArrayList<>();
		this.dir = dir;
          this.carrito = new Carrito(); 
	}
	

    private void estadoInicial() {
    	this.setEstado(new Borrador(this));
    }

    // VOIDS

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
    public void rembolsaCostoYEnvio(){} 
    public void rembolsaCosto() {} 
    public void rembolsaEnvio() {} 

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
}
