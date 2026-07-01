package main;

public class Confirmado extends Estado {

	
	public Confirmado(Pedido pedido) {
		// TODO Auto-generated constructor stub
	        this.pedido = pedido;
	    }
	
	public void mandarMail(Subsistema s) {
		s.enviarMail(this, pedido);
	};	
	public void mandarComprobante(Subsistema s) {};
	public void mandarCupon(Subsistema s) {};
}


