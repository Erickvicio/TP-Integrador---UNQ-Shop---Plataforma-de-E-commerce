package main;

public abstract class Estado {
	
	public Pedido pedido;
	
	public void siguiente(){}
	
	public void cancelado(){
		
		
	this.pedido.setEstado(new Cancelado(this.pedido));
		
	}
	
    public void agregarItem(Item item) {}
    public void quitarItem(Item item) {}
}
