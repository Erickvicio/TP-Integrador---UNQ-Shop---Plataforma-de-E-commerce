package servicioEnvio;

import main.Pedido;

public class Express implements TipoDeEnvio {

	private EnvioExpress lib;
	
	public Express(EnvioExpress lib) {
		this.lib = lib;
	}

	@Override
	public float calcularCostos(Pedido pedido) {
		return lib.calcularCosto(pedido.getPrecio());
	}

	@Override
	public int entregaEstimada(Pedido pedido) {
		return 1;
	}

}
