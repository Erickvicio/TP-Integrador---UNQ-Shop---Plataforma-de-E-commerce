package main;

public class Entregado extends Estado {

	
	public Entregado(Pedido pedido) {
		// TODO Auto-generated constructor stub
	        this.pedido = pedido;
	    }
	
	public void mandarMail(Subsistema s) {
		s.enviarMail(this, pedido);
	};	
	public void mandarComprobante(Subsistema s) {
		s.enviarComprobante(pedido);
	};
	public void mandarCupon(Subsistema s) {};

}
