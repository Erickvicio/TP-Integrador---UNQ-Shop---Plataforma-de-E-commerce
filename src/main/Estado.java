package main;

public abstract class Estado {
	
	Pedido pedido;
	
	public void mandarMail(Subsistema s) {};	
	public void mandarComprobante(Subsistema s) {};
	public void mandarCupon(Subsistema s) {};
	
	
}

