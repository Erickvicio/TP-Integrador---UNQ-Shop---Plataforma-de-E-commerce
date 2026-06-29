package servicioEnvio;

import main.Pedido;

public class RetiroEnSucursal implements TipoDeEnvio {
	
	private Sucursal sucursal;
	
	public RetiroEnSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public float calcularCostos(Pedido pedido) {
		return 0;
	}

	@Override
	public int entregaEstimada(Pedido pedido) {
		
		int ret = (sucursal.hayStock(pedido)) ? 1: 3;
		return ret;
	}

}
