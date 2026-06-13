package main;

public interface TipoDeEnvio {
	
	public float calcularCostos(Pedido pedido);
	
	public String entregaEstimada(Pedido pedido);
}
