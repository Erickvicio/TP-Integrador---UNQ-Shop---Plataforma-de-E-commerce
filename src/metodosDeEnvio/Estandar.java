package metodosDeEnvio;

import cicloDeVidaPedido.Pedido;

public class Estandar implements TipoDeEnvio {
	
	private CorreoArgentino lib;
	
	public Estandar(CorreoArgentino lib) {
		this.lib = lib;
	}

	@Override
	public float calcularCostos(Pedido pedido) {
		return lib.estimarEnvio(pedido.getPeso(), pedido.getDir());
	}

	@Override
	public int entregaEstimada(Pedido pedido) {
		return 7;
	}

}
