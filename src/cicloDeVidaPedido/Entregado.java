package cicloDeVidaPedido;

import catalogoDeProductos.ItemDeCatalogo;
import main.Item;
import notificacionesDePedido.Subsistema;

public class Entregado extends Estado {

    public Entregado(Pedido pedido) {
        this.pedido = pedido;
    }
	public void cancelado(){
		throw new RuntimeException("Error: Tarde, ya se entrego.");
	}
	@Override
    public void agregarItem(ItemDeCatalogo item) {
        throw new RuntimeException("Error: El flujo finalizó. El pedido ya fue entregado al cliente.");
    }

    @Override
    public void quitarItem(ItemDeCatalogo item) {
        throw new RuntimeException("Error: El pedido ya fue cerrado y entregado.");
    }
	
	@Override
	public void mandarMail(Subsistema s) {
		s.enviarMail(this, pedido);
	};	

	@Override
	public void mandarComprobante(Subsistema s) {
		s.enviarComprobante(pedido);
	}; 
}
