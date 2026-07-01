package cicloDeVidaPedido;

import catalogoDeProductos.ItemDeCatalogo;

public class Borrador extends Estado{
	
	
	// Constructor para pasarle el pedido al nacer el estado
    public Borrador(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
	public void siguiente(){
		this.pedido.setEstado(new Confirmado(this.pedido));
			pedido.decrementerStock();
	}
	
	
    public void agregarItem(ItemDeCatalogo item) {
        pedido.agregarItem(item);
    }

    public void quitarItem(ItemDeCatalogo item) {
    	pedido.quitarItem(item); 
    }
}
