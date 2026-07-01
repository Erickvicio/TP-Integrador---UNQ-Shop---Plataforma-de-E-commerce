package main; 

public class Pedido { 
     
    private Carrito carrito; 
    private Estado estado; 
    private Direccion dir; 

    // Constructor 
    public Pedido(Direccion dir) { 
        this.dir = dir; 
        this.carrito = new Carrito(); 
        this.estadoInicial(); 
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
}