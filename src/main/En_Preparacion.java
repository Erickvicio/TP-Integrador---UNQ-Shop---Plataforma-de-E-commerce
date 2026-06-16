package main;

public class En_Preparacion extends Estado {
	
	
    public En_Preparacion(Pedido pedido) {
        this.pedido = pedido;
    }
    
	public void cancelado(){	
	this.pedido.setEstado(new Cancelado(this.pedido));
	pedido.incrementarStock();
	pedido.rembolsaCostoYEnvio();
	}
	public void siguiente(){
		
	this.pedido.setEstado(new Enviado(this.pedido));
	}
	@Override
    public void agregarItem(Item item) {
        throw new RuntimeException("Error: El pedido ya está en preparación en el depósito.");
    }

    @Override
    public void quitarItem(Item item) {
        throw new RuntimeException("Error: No se pueden remover ítems de un pedido en preparación.");
    }
	
}
