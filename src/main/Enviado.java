package main;

public class Enviado extends Estado {

	
	public Enviado(Pedido pedido) {
		// TODO Auto-generated constructor stub
	        this.pedido = pedido;
	    }
	
	public void mandarMail(Subsistema s) {
		s.enviarMail(this, pedido);
	};	
	public void mandarComprobante(Subsistema s) {};
	public void mandarCupon(Subsistema s) {};

}
