package metodosDeEnvio;

import cicloDeVidaPedido.Pedido;

public interface TipoDeEnvio {
	
	public float calcularCostos(Pedido pedido);
	
	public int entregaEstimada(Pedido pedido);
}
