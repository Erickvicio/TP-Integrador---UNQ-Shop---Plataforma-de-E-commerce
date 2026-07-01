package main;

public interface Subsistema {
	public void update(Estado estado);
	public void enviarMail(Estado estado,Pedido p);
	public void enviarComprobante(Pedido p);
	public void enviarCupon(Pedido p);
	}
