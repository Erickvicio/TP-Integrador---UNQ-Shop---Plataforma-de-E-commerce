package main;

public class En_Preparacion extends Estado {


	
	public En_Preparacion(Pedido pedido) {
		// TODO Auto-generated constructor stub
	        this.pedido = pedido;
	    }
	
	public void mandarMail(Subsistema s) {};	
	public void mandarComprobante(Subsistema s) {};
	public void mandarCupon(Subsistema s) {};
}
