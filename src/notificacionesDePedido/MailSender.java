package notificacionesDePedido;

public interface MailSender {
	
	public void enviarMail(String direcciónDestino,String título,String mensaje, ArchivoAdjunto adjunto);

}
