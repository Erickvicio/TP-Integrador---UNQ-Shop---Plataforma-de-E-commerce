package main;

public class Cancelado extends Estado {

	
	public Cancelado(Pedido pedido) {
		// TODO Auto-generated constructor stub
	        this.pedido = pedido;
	    }
	
	public void mandarMail(Subsistema s) {};	
	public void mandarComprobante(Subsistema s) {};
	public void mandarCupon(Subsistema s) {
		s.enviarCupon(pedido);
	};
}
