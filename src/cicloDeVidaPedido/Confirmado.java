package cicloDeVidaPedido;

import main.Item;
import notificacionesDePedido.Subsistema;

public class Confirmado extends Estado {

    public Confirmado(Pedido pedido) {
        this.pedido = pedido;
    }
	public void cancelado(){
		
		
	this.pedido.setEstado(new Cancelado(this.pedido));
	pedido.incrementarStock();
		
	}
	
	public void siguiente(){
		this.pedido.setEstado(new En_Preparacion(this.pedido));
	}
	
	@Override
    public void agregarItem(Item item) {
        throw new RuntimeException("Error: El pedido ya está confirmado. No se pueden sumar más artículos.");
    }

    @Override
    public void quitarItem(Item item) {
        throw new RuntimeException("Error: El pedido ya está confirmado. Modificación denegada.");
    }

	@Override
	public void mandarMail(Subsistema s) {
		s.enviarMail(this, pedido);
	};
}
