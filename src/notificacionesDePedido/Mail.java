package notificacionesDePedido;

import cicloDeVidaPedido.Estado;

public class Mail extends ArchivoAdjunto {
	Estado estado;
	public Mail(String nombre,Estado estado,String texto) {
		super(nombre,texto);
		this.estado = estado;
	}

}
