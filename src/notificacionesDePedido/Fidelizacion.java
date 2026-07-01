package main;

public class Fidelizacion implements Subsistema {

	public Fidelizacion() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Estado estado) {
		// TODO Auto-generated method stub
		estado.mandarCupon(this);

	}

	@Override
	public void enviarMail(Estado estado, Pedido p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enviarComprobante(Pedido p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enviarCupon(Pedido p) {
		// TODO Auto-generated method stub
		ArchivoAdjunto comprobante= new Cupon("cupon", "cupon del 5% por estado cancelado");
		p.agregarArchivoAdjunto(comprobante);

	}

}
