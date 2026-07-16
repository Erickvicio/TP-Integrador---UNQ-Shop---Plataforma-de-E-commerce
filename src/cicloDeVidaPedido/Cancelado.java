package cicloDeVidaPedido;

import catalogoDeProductos.ItemDeCatalogo;
import notificacionesDePedido.Subsistema;

public class Cancelado extends Estado {
	
    public Cancelado(Pedido pedido) {
        this.pedido = pedido;
    }
    @Override
    public void agregarItem(ItemDeCatalogo item) {
        throw new RuntimeException("Error: El pedido está cancelado. Debe iniciar uno nuevo.");
    } 

    @Override
    public void quitarItem(ItemDeCatalogo item) {
        throw new RuntimeException("Error: No se pueden alterar los artículos de un pedido cancelado.");
    }

	@Override
	public void mandarCupon(Subsistema s) {
		s.enviarCupon(pedido);
	};
	
	public void siguiente() {
		pedido.notificarSubsitemas();
	}
	
}
