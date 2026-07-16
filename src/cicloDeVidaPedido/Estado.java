package cicloDeVidaPedido;

import catalogoDeProductos.ItemDeCatalogo;
import notificacionesDePedido.Subsistema;

public abstract class Estado {
	
	public Pedido pedido;
	
	public void siguiente(){}
	
	public void cancelado(){
		
		
	this.pedido.setEstado(new Cancelado(this.pedido));
		
	}
	
    public void agregarItem(ItemDeCatalogo item) {}
    public void quitarItem(ItemDeCatalogo item) {} 
    public void agregarItem(ItemDeCatalogo item,int veces) {}
  	public void quitarItem_veces(ItemDeCatalogo item,int veces) {} 

	public void mandarMail(Subsistema s) {};	
	public void mandarComprobante(Subsistema s) {};
	public void mandarCupon(Subsistema s) {};
	
	
	// El unico con implementacion es el Confirmado, el resto "tira error" (no hace nada)
	//public void iniciarProcesoDePago(float montoAPagar) {}
}  
