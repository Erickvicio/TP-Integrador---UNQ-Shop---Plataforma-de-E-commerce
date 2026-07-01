package metodosDeEnvio;

import cicloDeVidaPedido.Pedido;

public class ServicioEnvio {

	private Pedido pedido;
	private TipoDeEnvio tipo;
	
	public ServicioEnvio(Pedido pedido) {
		this.pedido = pedido;
	}

	public void establecerTipo(TipoDeEnvio tipo) {
		this.tipo = tipo;
	}

	public float costoEstimado() {
		return tipo.calcularCostos(pedido);
	}

	public int estimacionEntregaEnDias() {
		return tipo.entregaEstimada(pedido);
	}

}
