package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Confirmado;
import main.Direccion;
import main.Estado;
import main.Fidelizacion;
import main.GeneradordeFactura;
import main.MailSender;
import main.NotificadorDeMail;
import main.Pedido;
import main.Subsistema;

class SubsistemasEnEstadoConfirmadoTest {
	
	Pedido pedido;
	Estado confirmado;
	Subsistema notificadorDeMail;
	Subsistema fidelizacion;
	Subsistema generadorDeFactura;
	MailSender mailsender;
	Direccion dir;

	@BeforeEach
	void setUp() throws Exception {
		dir=mock(Direccion.class);
		pedido= new Pedido("lautaro@gmail.com",dir);
		confirmado= new Confirmado(pedido);
		mailsender= mock(MailSender.class);
		notificadorDeMail = new NotificadorDeMail(mailsender);
		fidelizacion = new Fidelizacion();
		generadorDeFactura=new GeneradordeFactura();
		
	}

	@Test
	void test() {
		pedido.setEstado(confirmado);
		pedido.agregarSubsitema(notificadorDeMail);
		pedido.agregarSubsitema(fidelizacion);
		pedido.agregarSubsitema(generadorDeFactura);
		pedido.notificarSubsitemas();
		
		//se agrego un ArchivoAdjunto mail por que es el unico subsistema que reacciona ante el estado confirmado
		assertEquals(pedido.getAdjuntos().size(),1);
		verify(mailsender).enviarMail("lautaro@gmail.com", "Mail de cambio de Estado", "te informamos que tu pedido cambio de estado",null);
	}

}
