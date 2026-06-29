package servicioEnvio;

import main.Pedido;

public interface TipoDeEnvio {
	
	public float calcularCostos(Pedido pedido);
	
	public int entregaEstimada(Pedido pedido);
}
