package main;

public class NotificadorDeMail implements Subsistema {
	
	MailSender mailsender;
	
	public NotificadorDeMail(MailSender mailsender) {
		this.mailsender=mailsender;
	}
	
	@Override
	public void update(Estado estado) {
		// TODO Auto-generated method stub
		estado.mandarMail(this);

	}

	@Override
	public void enviarMail(Estado e,Pedido p) {
		// TODO Auto-generated method stub
		ArchivoAdjunto mail=new Mail("Mail",e,"envio mail por cambio de estado");
		p.agregarArchivoAdjunto(mail);
		mailsender.enviarMail(p.getCorreo(), "Mail de cambio de Estado", "te informamos que tu pedido cambio de estado", null);
	}

	@Override
	public void enviarComprobante(Pedido p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enviarCupon(Pedido p) {
		// TODO Auto-generated method stub

	}


}
