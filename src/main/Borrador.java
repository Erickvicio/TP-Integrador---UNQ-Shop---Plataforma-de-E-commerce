package main;

public class Borrador extends Estado {
	
	Pedido pedido;
	
	public Borrador(Pedido pedido) {
		// TODO Auto-generated constructor stub
	        this.pedido = pedido;
	    }
	
	public void mandarMail(Subsistema s) {};	
	public void mandarComprobante(Subsistema s) {};
	public void mandarCupon(Subsistema s) {};
}

