package main;

public class Enviado extends Estado {

	public Enviado(Pedido pedido) {
        this.pedido = pedido;
    }
	
	public void cancelado(){
		
		
	this.pedido.setEstado(new Cancelado(this.pedido));
	pedido.incrementarStock();
	pedido.rembolsaCosto();
	}
	
	public void siguiente(){
		
	this.pedido.setEstado(new Entregado(this.pedido));
	}
	
	@Override
    public void agregarItem(Item item) {
        throw new RuntimeException("Error: El paquete ya fue despachado y está en camino.");
    }

    @Override
    public void quitarItem(Item item) {
        throw new RuntimeException("Error: Imposible quitar artículos. El transporte ya tiene el paquete.");
    }

	@Override
	public void mandarMail(Subsistema s) {
		s.enviarMail(this, pedido);
	};	
}
