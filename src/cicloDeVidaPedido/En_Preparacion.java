package cicloDeVidaPedido;

import catalogoDeProductos.ItemDeCatalogo;
import main.Item;

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
	public void agregarItem(ItemDeCatalogo item) {
        throw new RuntimeException("Error: El pedido ya está en preparación en el depósito.");
    }

    public void quitarItem(ItemDeCatalogo item) {
        throw new RuntimeException("Error: No se pueden remover ítems de un pedido en preparación.");
    }
	
}
