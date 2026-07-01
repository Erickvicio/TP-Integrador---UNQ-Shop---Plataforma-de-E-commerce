package cicloDeVidaPedido;

import main.Item;
import notificacionesDePedido.Subsistema;

public class Cancelado extends Estado {
	
    public Cancelado(Pedido pedido) {
        this.pedido = pedido;
    }
    @Override
    public void agregarItem(Item item) {
        throw new RuntimeException("Error: El pedido está cancelado. Debe iniciar uno nuevo.");
    }

    @Override
    public void quitarItem(Item item) {
        throw new RuntimeException("Error: No se pueden alterar los artículos de un pedido cancelado.");
    }

	@Override
	public void mandarCupon(Subsistema s) {
		s.enviarCupon(pedido);
	};
	
	
}
