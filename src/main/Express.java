package main;

public class Express implements TipoDeEnvio {

	private EnvioExpress lib;
	
	public Express(EnvioExpress lib) {
		this.lib = lib;
	}

	@Override
	public float calcularCostos(Pedido pedido) {
		// TODO Auto-generated method stub
		return lib.calcularCosto(pedido.getPrecio());
	}

	@Override
	public int entregaEstimada(Pedido pedido) {
		return 1;
	}

}
