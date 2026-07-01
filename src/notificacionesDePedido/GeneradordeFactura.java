package notificacionesDePedido;

import cicloDeVidaPedido.Estado;
import cicloDeVidaPedido.Pedido;

public class GeneradordeFactura implements Subsistema {

	public GeneradordeFactura() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Estado estado) {
		// TODO Auto-generated method stub
		estado.mandarComprobante(this);

	}

	@Override
	public void enviarMail(Estado estado, Pedido p) {
		// TODO Auto-generated method stub
 
	}

	@Override
	public void enviarComprobante(Pedido p) {
		// TODO Auto-generated method stub
		ArchivoAdjunto comprobante= new ComprobanteFiscal("comprobante", "combrobante fiscal por estado Entregado");
		p.agregarArchivoAdjunto(comprobante);
	}

	@Override
	public void enviarCupon(Pedido p) {
		// TODO Auto-generated method stub

	}

}
