package cicloDeVidaPedido;

import catalogoDeProductos.ItemDeCatalogo;
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
	
	public void agregarItemDeCatalogo(ItemDeCatalogo item) {
        throw new RuntimeException("Error: El pedido ya está confirmado. No se pueden sumar más artículos.");
    }

    public void quitarItemDeCatalogo(ItemDeCatalogo item) {
        throw new RuntimeException("Error: El pedido ya está confirmado. Modificación denegada.");
    }

	@Override
	public void mandarMail(Subsistema s) {
		s.enviarMail(this, pedido);
	};
}
